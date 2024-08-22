<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/adminHeader.jsp"%>
<fmt:bundle basename="resource.language">

	<%
		String rs = "0";
		try{
			rs = (request.getAttribute("rs").toString());
		} catch(Exception e) {
			rs = null;
		}
		
	%>
	<%
	// write_prop1 : '<fmt:message key="Write.prop1" />'
	//<fmt:message key="Login" />
	%>

	<div class="body-main">
	</div>
	
		<div class="modal" id="modal" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header" style="border-bottom:none !important">
		        <h5 class="modal-title" id="modal-title"><fmt:message key="Admin.Region"/></h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body" id="modal-body">
		      </div>
		      <div class="modal-footer" style="border-top:none !important">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="location.href ='/view/admin/regionSetting.jsp'"><fmt:message key="Admin.Region.Close"/></button>
		      </div>
		    </div>
		  </div>
		</div>
		
	<script>
		$(document).ready(function() {
			$("#modal-title").empty();
			$("#modal-body").empty();
			var planTitleMsg = '';
			var planMsg = '';
			
			planTitleMsg = '<fmt:message key="Admin.Region"/>';
			$("#modal-title").prepend(planTitleMsg);
			
			<% if( rs.equals("addS")) { %>
			
				$("#modal-body").empty();
				planMsg = '<fmt:message key="Admin.Region.Add.Success"/>';
				$("#modal-body").prepend(planMsg);
				$("#modal").show();
			
			<% } else if(rs.equals("addF")) { %>
			
				$("#modal-body").empty();
				planMsg = '<fmt:message key="Admin.Region.Add.Fail"/>';
				$("#modal-body").prepend(planMsg);
				$("#modal").show();
				
			<% } else if(rs.equals("editS")) { %>
				
				console.log(planMsg);
				$("#modal-body").empty();
				planMsg = '<fmt:message key="Admin.Region.Edit.Success"/>';
				$("#modal-body").prepend(planMsg);
				$("#modal").show();
				
			<% } else if(rs.equals("editF")) { %>
			
				$("#modal-body").empty();
				planMsg = '<fmt:message key="Admin.Region.Edit.Fail"/>';
				$("#modal-body").prepend(planMsg);
				$("#modal").show();
				
			<% } else if(rs.equals("delS")) { %>
					
				console.log(planMsg);
				$("#modal-body").empty();
				planMsg = '<fmt:message key="Admin.Region.Delete.Success"/>';
				$("#modal-body").prepend(planMsg);
				$("#modal").show();
					
			<% } else if(rs.equals("delF")) { %>
				
				$("#modal-body").empty();
				planMsg = '<fmt:message key="Admin.Region.Delete.Fail"/>';
				$("#modal-body").prepend(planMsg);
				$("#modal").show();
					
			<% } else { %>

				$("#modal-body").empty();
				planMsg = '<fmt:message key="Admin.Region.Error"/>';
				$("#modal-body").prepend(planMsg);
				$("#modal").show();
				
			<% } %>
		});

	</script>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>