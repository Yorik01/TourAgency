<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<%@ attribute name="user" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.User"%>

<table class="table bg-white table-bordered card mt-3 mr-3">
	<tbody>
		<tr>
			<td><fmt:message key="common.email" />: ${user.email}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.role" />: ${user.role}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.status" />: 
			 <c:choose>
			 	<c:when test="${user.isBanned() == 0}">
			 		<fmt:message key="common.active" />
			 	</c:when>
			 	<c:otherwise>
			 		<fmt:message key="common.banned" />
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
								<fmt:message key="common.ban" />
							</button>
					 	</c:when>
					 	<c:otherwise>
				 			<input type="hidden" name="action" value="unbannUser" />
							<button class="btn btn-danger" type="submit">
								<fmt:message key="common.unban" />
							</button>
			 			</c:otherwise>
					</c:choose>
				</form>
			</td>
		</tr>
	</tbody>
</table>