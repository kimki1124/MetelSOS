<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<title>+++  MetelSOS  +++</title>
		<%@ include file="/common/include/include-header.jsp" %>
		<script src="/metelSOS/resources/js/common.js" charset="utf-8"></script>
		<script>
			//세션없으면 로그인 페이지로 이동
			var sessionId = '<%=session.getAttribute("SESSION_LOGIN_USER_ID")%>';
			if(sessionId == 'null'){
				document.location.href="/metelSOS/login.jsp";
			}
		</script>
</head>
<body class="">
		<jsp:include page = "/common/header/HeaderPanel.jsp" flush="false"/>
		<jsp:include page = "/common/left/engineer/EngineerLeftPanel.jsp" flush="false"/>
		<form name="noticePageParamForm">
			<input type="hidden" name="userType">
			<input type="hidden" name="menuTitle">
			<input type="hidden" name="menuIcon">
		</form>
		<!-- #MAIN PANEL -->
		<div id="main" role="main">

			<!-- RIBBON -->
			<div id="ribbon">

				<!-- breadcrumb -->
				<ol class="breadcrumb">
					<li>Home</li>
					<c:forEach var="item" items="${breadcrumbList}" varStatus="status">
						<li>${item}</li>
					</c:forEach>
					<!-- <li>메인화면</li> -->
				</ol>
				<!-- end breadcrumb -->
			</div>
			<!-- END RIBBON -->

			<!-- #MAIN CONTENT -->
			<div id="content">
				
				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h3 class="page-title txt-color-blueDark">
							<i class="${menuIcon}"></i> 
								${menuTitle} 
						</h3>
					</div>
				</div>
				
				<section id="widget-grid" class="">
					<div class="row">
						<article class="col-sm-6">
							<div class="jarviswidget jarviswidget-color-darken" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>${currYear }년 ${selectMonth }월 신입사원</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div class="table-responsive">
											<table class="table table-bordered" id="newEmplydTable">
												<thead>
													<tr>
														<th style="width:12.5%;">번호</th>
														<th style="width:25%;">소속 부서</th>
														<th style="width:25%;">사원명</th>
														<th style="width:25%;">직급</th>
														<th style="width:12.5%; text-align:center;">삭제</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${newEmplydList}" varStatus="status">
														<tr>
															<td>${status.count }</td>
															<td>${item.engineer_dept }</td>
															<td>${item.engineer_name }</td>
															<td>${item.engineer_position }</td>
															<td style="text-align:center;"><a href="javascript:deleteNewEmplyd('${item.engineer_dept }', '${item.engineer_name }')"><i class="fa fa-ban fa-lg" aria-hidden="true"></i></a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="widget-body" style="min-height:40px;">
										<button id="showNewEmplydForm" class="btn btn-success">신입사원 등록</button>
										<button id="moveYearMonthList" class="btn btn-success" style="float:right;">목록으로</button>
									</div>
								</div>
							</div>
						</article>
						<article class="col-sm-6">
							<div class="jarviswidget jarviswidget-color-darken" id="newemplydadd" data-widget-editbutton="false" data-widget-deletebutton="false" style="display:none;"><!-- style="display:none;" -->
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>신입사원 등록</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body no-padding">
										<form id="newemplyd_add_form" name="newemplyd_add_form" class="smart-form" enctype="multipart/form-data">
											<fieldset>
												<section>
													<label class="label">부서</label>
													<label class="select">
														<select id="deptSelect" name="engineer_dept">
															<option value="default" selected="" disabled="">부서를 선택해 주세요.</option>
															<c:forEach var="item" items="${deptList}" varStatus="status">
																<option value="${item }">${item }</option>
															</c:forEach>
														</select> <i></i> </label>
												</section>
												<section>
													<label class="label">사원명</label>
													<label class="select">
														<select id="nameSelect" name="engineer_name">
															<option value="default" selected="" disabled="">사원을 선택해 주세요.</option>
														</select> <i></i> </label>
												</section>
												<section>
													<label class="label">사원 이미지</label>
													<div class="input input-file">
														<span class="button"><input type="file"name="engineer_image" onchange="this.parentNode.nextSibling.value = this.value">찾기</span><input type="text" placeholder="사원 이미지를 등록해주세요." readonly="">
													</div>
													<div class="note">
														<strong>참고:</strong> 높이는 270px로 수정 후 업로드 해주세요.
													</div>
												</section>
												<section>
													<label class="label">신입사원 각오</label>
													<label class="textarea"> 										
														<textarea rows="1" id="impression_speech" class="custom-scroll" name="impression_speech"></textarea> 
													</label>
													<div class="note">
														<strong>참고:</strong> 최대한 간결하게 써주세요.
													</div>
												</section>
											</fieldset>
											<footer>
												<button type="submit" id="enrollExclntStf" class="btn btn-success">등록</button>
												<button id="moveNewEmplydList" class="btn btn-danger">취소</button>
											</footer>
										</form>
									</div>
								</div>
							</div>
						</article>
					</div>
				</section>
			</div>	
		</div>
		<!-- END #MAIN PANEL -->

		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />

		<%@ include file="/common/include/include-body.jsp" %>
		
		<!-- Your GOOGLE ANALYTICS CODE Below -->
		<script type="text/javascript">
		 	 var _gaq = _gaq || [];
		 	 _gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
		 	 _gaq.push(['_trackPageview']);
		
		  	(function() {
		   		 var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		   		 ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		   	 	var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  	})();
		  	
		  	$.validator.addMethod("valueNotEquals", function(value, element, arg){
				return value != null;
			}, "Value must not equal arg.");
		  	
		  	$("#newemplyd_add_form").validate({

				// Rules for form validation
				rules : {
					engineer_dept : {
						valueNotEquals : 'default'
					},
					
					engineer_name : {
						valueNotEquals : 'default'
					}
					
				},

				// Messages for form validation
				messages : {
					engineer_dept : {
						valueNotEquals : '부서를 선택해주세요.'
					},
					
					engineer_name : {
						valueNotEquals : '사원을 선택해주세요.'
					}
				},
				
				invalidHandler: function(form, validator) {
		             return false;
		        },
		        
		        submitHandler: function (form) {
		        	var formData = new FormData();
		        	var specifyDate = '${currYear}-${selectMonth}-01';
		        	formData.append("new_emply_year_month", specifyDate);
		        	formData.append("engineer_dept", $("#deptSelect").val());
		        	formData.append("engineer_name", $("#nameSelect").val());
		        	formData.append("engineer_image", $("input[name=engineer_image]")[0].files[0]);
		        	formData.append("impression_speech", $("#impression_speech").val());
		        	$.ajax({
						url:'/metelSOS/enrollNewEmplyd.do',
						type:'POST',
						processData: false,
		        	    contentType: false,
						data: formData,
						success:function(msg){
							var obj = eval("("+msg+")");
							if(obj.resultMsg == 'SUCCESS'){
					  			var comSubmit = new ComSubmit();
					  			comSubmit.setUrl("/metelSOS/moveNewEmplydListPage.do");
					  			comSubmit.addParam('currYear', '${currYear}');
					  			comSubmit.addParam('selectMonth', '${selectMonth}');
					  			comSubmit.addParam('userType', 'engineer');
					  			comSubmit.addParam('menuTitle', encodeURI("${currYear}년 ${selectMonth}월 신입사원 "));
					  			comSubmit.addParam('menuIcon', 'fa fa-lg fa-fw fa-male');
					  			comSubmit.getSubmit();
							}
						},
						error:function(request,status,error){
							alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					    }
					});
		            
		        }
			});
		  	
		  	$(document).ready(function(){
		  		pageSetUp();
		 	 });
		  	
		  	$("#showNewEmplydForm").click(function(e){
		  		e.preventDefault();
		  		$("#newemplydadd").css("display", '');
		  	});
		  	
		  	$("#moveNewEmplydList").click(function(e){
		  		e.preventDefault();
		  		$("#newemplydadd").css('display', 'none');
		  	});
		  	
		  	$("#moveYearMonthList").click(function(e){
		  		e.preventDefault();
		  		var comSubmit = new ComSubmit();
	  			comSubmit.setUrl("/metelSOS/moveManageNewEmplydPage.do");
	  			comSubmit.addParam('userType', "engineer");
	  			comSubmit.addParam('menuTitle', encodeURI("신입사원 관리"));
	  			comSubmit.addParam('menuIcon', "fa fa-lg fa-fw fa-male");
	  			comSubmit.getSubmit();
		  	});
		  	
		  	$("#deptSelect").change(function(){
		  		$( "#deptSelect option:selected" ).each(function() {
		  			var dept = $( this ).text();
		  			$.ajax({
		  				url:'/metelSOS/setNetEmplydNameList.do',
		  				type:'POST',
		  				dataType:'json',
		  				data:{"engineer_dept" : encodeURI(dept), "currYear" : '${currYear}', "selectMonth" : '${selectMonth}'},
		  				success:function(msg){
		  					var nameList = msg.engineerNameList;
		  					$('option', $("#nameSelect")).not(':eq(0), :selected').remove();
		  					
		  					for(var i=0;i<nameList.length;i++){
		  						$("#nameSelect").append($("<option></option>").attr("value",nameList[i]).text(nameList[i]));
		  					}
		  				},
		  				error:function(request, status, error){
		  					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		  				}
		  			});
		  	    });
		  	});
		  	
		  	function deleteNewEmplyd(dept, name){
		  		$.ajax({
					url:"/metelSOS/deleteNewEmplyd.do",
					type:'POST',
					dataType:'json',
					data:{ "engineer_dept" : encodeURI(dept), "engineer_name" : encodeURI(name) },
					success:function(msg){
						if(msg.resultMsg == 'SUCCESS'){
							$("#newEmplydTable tr").each(function(){
					  			if($(this)[0].children[1].innerHTML == msg.engineer_dept && $(this)[0].children[2].innerHTML == msg.engineer_name){
					  				$(this)[0].remove();
					  			}
					  		});
						}
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});
		  	};
		</script>

	</body>
</html>