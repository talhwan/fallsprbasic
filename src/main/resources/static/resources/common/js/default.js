/* ajax 관련 */
function func_ajax(_data) {
	let paramData = null;
	//alert(JSON.stringify(_data.param));
/*
	let formData = new FormData();
	formData.forEach((value, key) => _data.param[key] = value);
	let data2 = $.param(_data.param);
	alert(JSON.stringify(data2));
	//return false;
*/
	if(isNull(_data.param)){
	} else {
		if(_data.type === "GET"){
			paramData = $.param(_data.param);
		} else {
			paramData = JSON.stringify(_data.param);
		}
	}

	$.ajax({
		url: _data.url,
		type: _data.type,
		beforeSend: function (xhr) {
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.setRequestHeader("Authorization", localStorage.getItem("accessToken"));
		},
		contentType : 'application/json; charset=utf-8',
		//data: JSON.stringify(_data.param),
		data: paramData,
		cache: false,
		success: (data, status, xhr)=>{
			// alert(JSON.stringify(data));
			// success
			if (xhr.status >= 200 && xhr.status < 300) {
				_data.success(data, status, xhr);
			} else {
				alert("error occured. try again");
			}
		},
		error: (data, status, xhr)=>{
			/*
			alert(data.status);
			alert(JSON.stringify(data));
			 */
			// error
			if(data.status === 401){
				//access token 만료
				if(_data.retry == null || _data.retry == false){
					//access token 만료되었을때 다시 시도
					//alert("access token expired!");
					_data.retry = true;
					access_token(_data);
				} else {
					alert("please login");
					//location.replace("/tbuser/snslogin");
				}
			} else if(data.status === 403){
				// 권한이 없음.
				alert(_data["url"] + " : no access auth.");
			} else if(data.status === 406){
				//refresh token 만료
				alert("please login");
				//location.replace("/tbuser/snslogin");
			} else if(data.status === 409){
				alert("중복된 정보입니다. 다시 시도해주세요.");
			} else if(data.status === 423){
				alert("회원 등급에 문제가 있음.");
				location.replace("/tbuser/process");
			} else {
				_data.error(data, status, xhr);
			}
		},
	});
}
function access_token(_data){
	$.ajax({
		url: "/api/auth/access-token",
		method: "POST",
		beforeSend: function (xhr) {
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.setRequestHeader("RefreshToken", localStorage.getItem("refreshToken"));
		},
		cache: false,
		success: (data, status, xhr)=>{
			switch(xhr.status){
				case 200:
					let accessToken = xhr.getResponseHeader("Authorization");
					localStorage.setItem("accessToken", accessToken);
					func_ajax(_data);
					break;
				default:
					alert("error occured. try again");
					console.log("no matching status code");
					break;
			}
		},
		error: (data, status, xhr)=>{
			//alert(JSON.stringify(data));
			switch(data.status){
				case 401:
					alert("expired refresh token. login please");

					localStorage_clear();
					location.replace("/tbuser/login");
					//location.replace("/tbuser/snslogin");
					break;
			}
		},
	});
}
function readURLFile(input, listener_after_upload) {
	let limit_each_file_size = 10;
	let temp_id = $(input).attr("id") + "";
	if (input.files && input.files[0]) {
		let reader = new FileReader();
		reader.readAsDataURL(input.files[0]);
		reader.onload = function(e) {
			let temp_each_file_size = input.files[0].size / (1024 * 1024);
			if (temp_each_file_size > limit_each_file_size) {
				alert("파일 1개 당 " + limit_each_file_size + "mb 용량 제한 초과 입니다!");
				$(input).val("");
				return false;
			}
			let file_type = "image";
			if (!input.files[0].type.match('image.*')) {
				file_type = "file";
			} else {
				file_type = "image";
			}
			listener_upload_file(input.files[0], file_type, listener_after_upload);
		}
	}
}
function listener_upload_file(obj_file, file_type, listener_after_upload) {
	let fileData = new FormData();
	fileData.append("file", obj_file);

	$.ajax({
		url: "/api/default/uploadFile",
		type: "POST",
		data: fileData ,
		cache : false,
		contentType : false,
		processData : false,
		success: (data, status, xhr)=>{
			//alert(xhr.status);
			switch(xhr.status){
				case 201:
					//alert(data);
					listener_after_upload(file_type, data.url);
					break;
				default:
					console.log("no matching status code");
			}
		},
		error: (data)=>{
			alert("error")
		},
	});
}

/* list관련 기능 */
function set_search_sdatefdate(obj){
	var temp_d = new Date();
	$("#search_fdate").val(dateToStringFormat(temp_d));

	switch(obj){
		case 'null':
			$("#search_fdate").val("");
			$("#search_sdate").val("");
			break;
		case 'today':
			$("#search_sdate").val(dateToStringFormat(temp_d));
			break;
		case 'week':
			temp_d.setDate(temp_d.getDate() - 7);
			$("#search_sdate").val(dateToStringFormat(temp_d));
			break;
		case 'month':
			temp_d.setMonth(temp_d.getMonth() - 1);
			$("#search_sdate").val(dateToStringFormat(temp_d));
			break;
	}
	listener_keyword_changed();
}
function set_search_keyword(){
	let search_keyword = $("#search_keyword").val();
	let search_keyword_each = $(".search_keyword_each");
	for (let i = 0; i < search_keyword_each.length; i++) {
		let t_name = $(search_keyword_each[i]).attr("name") + "";
		$(search_keyword_each[i]).val("");
	}
	listener_keyword_changed();

	$(".search_keyword_each").removeClass("hide");
	$(".search_keyword_each").addClass("hide");
	$(".search_keyword_each_" + search_keyword).removeClass("hide");
}
function check_chk(obj_class){
	let all_checked = true;
	let input_each = $("." + obj_class + "_each");
	for(let i=0;i<input_each.length;i++){
		let each_checked = $(input_each[i]).prop("checked");
		if(each_checked){
		} else {
			all_checked = false;
		}
	}
	$("." + obj_class + "_all").prop("checked", all_checked);
}
function check_chk_delete(){
	check_chk("input_chk_delete");
}
function listener_chk(obj, obj_class){
	let keyword = $(obj).attr("keyword");
	let checked = $(obj).prop("checked");
	//alert(checked);
	switch(keyword){
		case "all" :
			if(checked){
				$("."+obj_class+"_each").prop("checked", true);
			} else {
				$("."+obj_class+"_each").prop("checked", false);
			}
			break;
		default :
			check_chk(obj_class);
			break;
	}
}
function listener_chk_delete(obj){
	listener_chk(obj, "input_chk_delete");
}

function listenerGetDeleteIds(){
	let ids = [];
	let input_chk_delete_each = $(".input_chk_delete_each");
	for(let i=0;i<input_chk_delete_each.length;i++){
		let each_checked = $(input_chk_delete_each[i]).prop("checked");
		if(each_checked){
			ids.push($(input_chk_delete_each[i]).attr("keyword"));
		}
	}
	return ids;
}
function listenerBeforeList(tbody_id){
	let search_cway = $("#search_cway").val() + "";
	let cdatetime = "";
	if(search_cway == "recent"){
		cdatetime = $("#search_fdatetime").val() + "";
	} else {
		cdatetime = $("#search_sdatetime").val() + "";
	}
	let input_keyword_changed = $("#input_keyword_changed").val() + "";
	if(input_keyword_changed == "1"){
		search_cway = "before";
		$("#search_cway").val(search_cway);
		cdatetime = "";
		$("#search_sdatetime").val("");
		$("#search_fdatetime").val("");
		$("#" + tbody_id).html("");
		$("#input_keyword_changed").val("0");
	}
	let returnVal = {
		'sdate' : $("#search_sdate").val(),
		'fdate' : $("#search_fdate").val(),
		'cdatetime' : cdatetime,
		'cway' : search_cway,
		'permore' : $("#search_permore").val()
		,'deleted' : $("#search_deleted").val()
	};

	return returnVal;
}
function listenerAfterListEach(this_created_at){
	//초기값 설정
	let search_sdatetime = $("#search_sdatetime").val() + "";
	if(search_sdatetime == ""){
		$("#search_sdatetime").val(this_created_at);
	}
	let search_fdatetime = $("#search_fdatetime").val() + "";
	if(search_fdatetime == ""){
		$("#search_fdatetime").val(this_created_at);
	}
	if(search_sdatetime > this_created_at){
		$("#search_sdatetime").val(this_created_at);
	}
	if(search_fdatetime < this_created_at){
		$("#search_fdatetime").val(this_created_at);
	}
}
function listenerAfterList(){
	let font_order = $(".font_order");
	for (let t = 0; t < font_order.length; t++) {
		$(font_order[t]).text((t+1));
	}

	let select_search_keyword = $(".select_search_keyword");
	for (let t = 0; t < select_search_keyword.length; t++) {
		let select_temp_name = $(select_search_keyword[t]).attr("keyword");
		let option_temps = $(select_search_keyword[t]).find("option");
		for(let i=0;i<option_temps.length;i++){
			let a_html = $(option_temps[i]).html();
			let a_value = $(option_temps[i]).attr("value");
			$(".font_"+ select_temp_name +"_" + a_value).html(a_html);
		}
	}
}

/* create관련 기능 */
function listenerBeforeCreate(){
	let input_required = $(".input_required");
	for(let i=0;i<input_required.length;i++){
		if ($.trim($(input_required[i]).val()) == "") {
			alert($(input_required[i]).attr("placeholder"));
			$(input_required[i]).focus();
			return false;
		}
	}
	return true;
}
/* detail관련 기능 */
function listenerAfterDetail(obj_data, obj){
	let detailKeys = Object.keys(obj_data)
	for (let eachKey of detailKeys){
		$(".font_"+obj+"_" + eachKey).text(obj_data[eachKey]);

		let eachType = $("#detail_"+obj+"_" + eachKey).attr("type");
		if(eachType === "checkbox"){
			$("#detail_"+obj+"_" + eachKey).prop("checked", obj_data[eachKey]);
		} else if(eachType === "radiotext"){
			$('input[name="'+obj +'_'+eachKey+'"][value="'+obj_data[eachKey]+'"]').prop("checked",true);
			$("#detail_"+obj+"_" + eachKey).val(obj_data[eachKey]);
		} else {
			$("#detail_"+obj+"_" + eachKey).val(obj_data[eachKey]);
			if($("#detail_"+obj+"_" + eachKey).prop('tagName') === "SELECT"){
				$(".font_"+obj+"_" + eachKey).text($("#detail_"+obj+"_" + eachKey + " option:selected").text());
			}
		}

		$("#img_"+obj+"_" + eachKey).attr("src", obj_data[eachKey]);
	}
}
/* js 추가 기능 */
function isNull(x) {
	let result_x = false;
	x = x + "";
	if(x == null || x === "null" || x === "" || x === "undefined"){
		result_x = true;
	} else {
	}
	return result_x;
}
function number2digit(x) {
	var return_val = x + "";
	if(Number(x) < 10){
		return_val = "0" + return_val;
	}
	return return_val;
}
function numberWithCommas(x) {
	x = x + "";
	let result_x = "";
	let result_x_head = "";
	let result_x_tail = "";
	if(x.indexOf(".") > -1){
		let split_result_x = x.split('.');
		result_x_head = split_result_x[0];
		result_x_tail = "." + split_result_x[1];
	} else {
		result_x_head = x;
	}
	result_x = result_x_head.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + result_x_tail
	return result_x;
}

function getNextDay(date_string, days) {
	let date = null;
	if(date_string == null){
		date = new Date();
	} else {
		date = new Date(date_string);
	}
	if(days == null){
		days = 1;
	}
	date.setDate(date.getDate() + days);
	let d = date.getDate();
	let m = date.getMonth();
	let y = date.getFullYear();
	let temp_today = y + "-" + number2digit(m+1) + "-" + number2digit(d) + "";
	return temp_today;
}
function dateToStringFormat(date) {
	if(date == null){
		date = new Date();
	}
	let d = date.getDate();
	let m = date.getMonth();
	let y = date.getFullYear();
	let temp_today = y + "-" + number2digit(m+1) + "-" + number2digit(d) + "";
	return temp_today;
}
function getIdFromUrl(obj){
	let temp_url_with_idx = obj;
	if(obj == null){
		temp_url_with_idx = window.location.href;
	}
	let split_slash_temp_idx = temp_url_with_idx.split('/');
	let temp_idx = split_slash_temp_idx[split_slash_temp_idx.length - 1];
	let split_question_temp_idx = temp_idx.split('?');
	if(split_question_temp_idx.length > 0){
		temp_idx = split_question_temp_idx[0];
	}
	return temp_idx;
}
function listener_maxlength_check(obj, obj1){
	let this_temp_val = $(obj).val();
	let this_temp_max = Number(obj1);
	if(this_temp_val.length > this_temp_max){
		alert("최대 "+ this_temp_max +"자까지 입력 가능합니다.");
		$(obj).val(this_temp_val.substring(0, this_temp_max));
	}
}
function location_href_path_param(obj_param, obj_add_path){
	location.href = obj_param + "" + obj_add_path;
}
function getSprExceptionMsg(obj_param){
	let obj = JSON.parse(obj_param['responseText']);
	let stringTrace = JSON.stringify(obj['trace']);
	let index0 = stringTrace.indexOf("com.thc.sprapi.exception.SprException");
	let index1 = stringTrace.indexOf("\\r");
	let tempStringTrace = stringTrace.substring(index0, index1);
	tempStringTrace = tempStringTrace.replace("com.thc.sprapi.exception.SprException: ", "");
	return tempStringTrace;
}
function listener_detect_bottom() {
	let scrollTop = $(window).scrollTop();
	let innerHeight = $(window).innerHeight();
	let scrollHeight = $('body').prop('scrollHeight');
	return scrollTop + innerHeight >= scrollHeight;
}
function listener_detect_top(obj_top) {
	let scrollTop = $(window).scrollTop();
	return scrollTop <= obj_top;
}
function listener_copy_clipboard(obj_text) {
	window.navigator.clipboard.writeText(obj_text).then(() => {
		alert(obj_text + " 복사 완료!");
	});
}