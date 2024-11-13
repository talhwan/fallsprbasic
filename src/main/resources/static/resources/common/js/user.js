function logout(){
	let _data = new Map();
	_data.url = "/api/tbuser/logout";
	_data.type = "POST";
	_data.param = null;
	_data.success = function(obj_data, obj_status, obj_xhr){
		localStorage_clear();
		location.replace("/index");
	}
	_data.error = function(obj_data, obj_status, obj_xhr){
		localStorage_clear();
		location.replace("/index");
	}
	func_ajax(_data);
}
function localStorage_clear(){
	let device_uuid = null;
	if(localStorage.getItem('device_uuid') != null){
		device_uuid = localStorage.getItem('device_uuid') + "";
	}
	localStorage.clear();
	if(device_uuid != null){
		localStorage.setItem('device_uuid', device_uuid);
	}
}
