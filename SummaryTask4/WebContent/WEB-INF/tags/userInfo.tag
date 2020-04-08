<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ attribute name="user" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.User"%>

<table class="table bg-white table-bordered card mt-3 mr-3">
	<tbody>
		<tr>
			<td>Email: ${user.email}</td>
		</tr>
		<tr>
			<td>Role: ${user.role}</td>
		</tr>
		<tr>
			<td>Status: 
			 <c:choose>
			 	<c:when test="${user.isBanned() == 0}">
			 		Active
			 	</c:when>
			 	<c:otherwise>
			 		Banned
			 	</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<form method="post" action="/TourAgency/controller">
					<input type="hidden" name="id" value="${user.id}" />
					
					 <c:choose>
			 			<c:when test="${user.isBanned() == 0}">
					 		<input type="hidden" name="action" value="bannUser" />
							<button class="btn btn-danger" type="submit">
								Bann
							</button>
					 	</c:when>
					 	<c:otherwise>
				 			<input type="hidden" name="action" value="unbannUser" />
							<button class="btn btn-danger" type="submit">
								Unbann
							</button>
			 			</c:otherwise>
					</c:choose>
				</form>
			</td>
		</tr>
	</tbody>
</table>