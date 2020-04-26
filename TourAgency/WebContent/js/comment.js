const PATH = '/TourAgency/controller';

let errMessage;

let userId;

let commentId;

let tourId;

const setErrMessage = (msg) => {
    errMessage = msg;
};

const getCommentId = () => $('#comment-id').val(); 

const setTourId = (id) => {
    tourId = id;
};

const setUserId = userIdVal => {
	userId = userIdVal;
};

const fillForm = () => {    
    let commentCard;
    
    $('.comment-user-id').each(function() {
	if ($(this).val() === userId) {
	    commentCard = $(this).parent('div').parent('div');
	}
    });
    
    tourId = commentCard.find('.comment-tour-id').val();
    let commentText = commentCard.find('.comment-text').text();
    let commentMark = commentCard.find('.comment-mark').text();

    $('#input-comment-text').val(commentText);
    $('#input-comment-mark').val(commentMark);
}; 

const deleteComment = () => {
  $.post(
	  PATH,
	  {
	      action: 'deleteComment',
	      commentId: commentId
	  }
  )
  	.done(() => {
  	    document.location.reload(true);
  	})
	.fail(() => {
	    $("#info-modal-message").text(errMessage);
	    $("#info-modal").modal('show');
	});
};

const saveComment = (tourId, text, mark) => {
	$.post(
			PATH,
			{
				action: 'saveComment',
				edit: false,
				userId: userId,
				tourId: tourId,
				commentText: text,
				commentMark: mark
			}
		)
		.done(() => {
			document.location.reload(true);
		})
		.fail(() => {
			$("#info-modal-message").text(errMessage);
			$("#info-modal").modal('show');
		});
};

const editComment = (tourId, text, mark, commentId) => {
	$.post(
			PATH,
			{
				action: 'saveComment',
				edit: true,
				userId: userId,
				tourId: tourId,
				commentText: text,
				commentMark: mark,
				commentId: commentId
			}
		)
		.done(() => {
			document.location.reload(true);
		})
		.fail(() => {
			$("#info-modal-message").text(errMessage);
			$("#info-modal").modal('show');
		});
};

$(document).ready(() => {
	let firstSlide = $('.carousel-item')[0];
	$(firstSlide).addClass('active');
	
	commentId = getCommentId();

	const saveCommentUtil = (commentId) => {
		let commentTextVal = $('#input-comment-text').val();
		let commentMarkVal = $('#input-comment-mark').val();

		if (commentId === "") {
			saveComment(
					tourId,
					commentTextVal, 
					commentMarkVal);
		} else {
			editComment(
					tourId,
					commentTextVal, 
					commentMarkVal,
					commentId);
		}
		
	};
	
	$('#btn-save-comment').click(() => {
	    saveCommentUtil(commentId);
	    commentId = null;
	});
	
	$('.btn-edit-comment').click(function() {
	    fillForm();
	});
	
	$('#btn-confirm-delete').click(function () {
	    deleteComment();
	});
});