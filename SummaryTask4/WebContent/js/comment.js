const saveComment = (userId, tourId, text, mark) => {
	$.post(
			'/TourAgency/controller',
			{
				action: 'saveComment',
				edit: false,
				userId: userId,
				tourId: tourId,
				commentText: text,
				commentMark: mark
			}
		);
	})
		.done(() => {
			document.location.reload(true);
		})
		.fail(() => {
			$("#info-modal-message").text("Error of saving comment!");
			$("#info-modal").modal('show');
		});
};

const saveComment = (userId, tourId, text, mark, commentId) => {
	$.post(
			'/TourAgency/controller',
			{
				action: 'saveComment',
				edit: true,
				userId: userId,
				tourId: tourId,
				commentText: text,
				commentMark: mark,
				commentId: commentId
			}
		);
	})
		.done(() => {
			document.location.reload(true);
		})
		.fail(() => {
			$("#info-modal-message").text("Error of saving comment!");
			$("#info-modal").modal('show');
		});
};