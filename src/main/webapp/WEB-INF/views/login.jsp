<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login_You Web</title>
<script src="http://maps.google.com/maps/api/js?sensor=true"></script> 
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/ajaxfileupload.js"></script>

</head>
<body>
  <script> 

  $(document).ready(function() { 
	  var $type_countryObj=$("select[id$='type-country']");
	  var $type_cityObj=$("select[id$='type-country-city']");
	  var $userIpObj=$(":input[name^='userIp']");
	  $type_countryObj.val("");
	  $type_countryObj.each(function(index){
		  $(this).bind('change',function(){
			  var optionTw =["<option value=''>選擇城市</option>","<option value='007'>台北</option>","<option value='012'>新北</option>","<option value='008'>台中</option>","<option value='009'>高雄</option>","<option value='010'>花蓮</option>","<option value='011'>台東</option>","<option value='013'>台南</option>",
			                    "<option value='014'>桃園</option>","<option value='016'>基隆</option>"];
			  var optionJp =["<option value=''>選擇城市</option>","<option value='001'>東京</option>","<option value='002'>橫濱</option>","<option value='003'>大阪</option>","<option value='004'>名古屋</option>"];;
			  var optionUsa=["<option value=''>選擇城市</option>","<option value='001'>New York</option>","<option value='002'>Los Angeles</option>","<option value='003'>Chicago</option>","<option value='004'>Houston</option>"];;
		  	  $type_cityObj.eq(index).empty();
		  	  switch($(this).val()){
		  	  	case "1":
		  		  $type_cityObj.eq(index).append(optionTw);
		  		  break;
		  		case "2":
		  		  $type_cityObj.eq(index).append(optionJp);
		  		  break;
		  		case "3":
			  	  $type_cityObj.eq(index).append(optionUsa);
			  	  break;
			  	default:
			      $type_cityObj.eq(index).empty();
		  	  }
		  });
	  });
	  
	  $("#checkIp").click(function(){
			$.post("/rest-server-simple/simple/getIpAddress",function(sIPAddress){
				if(sIPAddress != ""){
					$userIpObj.val(sIPAddress);
				}
			}); 
		 });
	  $("#buttonUpload").click(function(){
		if($("#fileToUpload").val().length >0){
		  $.ajaxFileUpload  
		     ( {
				url:'/rest-server-simple/simple/upLoadImg',
				secureuri:false,  
	            fileElementId:'fileToUpload',  
	            dataType: 'text', 
				success:function(data,status){
						$("#mypic").attr("src", data); 
		        },  
				error:function(data,status,e){
					alert(e);
				}
			});	 
		}else{
			alert("請選擇圖片");
		}
	  });
  }); 
  </script>
  <div>
    <h2>用戶：</h2><input type="text" placeholder="請輸入用戶名" id="w-input-userId" value=""> 
  </div>
  <div>
    <h2>來自：</h2><select id=type-country name="type" ><!-- onchange="updateData(this)" -->
      <option value="">請選擇國家</option>
      <option value="1">台灣</option>
      <option value="2">日本</option>
      <option value="3">美國</option>
    </select>
    <select id=type-country-city name="city">
      <option value="" selected>選擇出發地</option>
    </select>
  </div>
  <div>
	    <h2>定位ip:</h2><input type="text" name="userIp" id="w-input-userIp" value="113.196.44.28"> 
	    <input type="button" value="ajaxCheckIp" id="checkIp">
  </div>
  <div>
    <h2>頭像:</h2>
	    <form id="uploadForm">
	    	<input type="file" value="選擇大頭照" name="fileToUpload" id="fileToUpload"><font color="#999999">
	    <span>&nbsp;支持jpg格式</span></font>
	    </form>
    	<input type="button" id="buttonUpload" value="Upload"/>
    <div id = "pic">
  		<img id="mypic" src="${pageContext.servletContext.contextPath}/resources/img/agee.jpg" width="150" height="200"/>
    </div>
  </div>
</body>
</html>