// JavaScript Document

function backToHome(){
	window.location.href ="index.jsp";
}
function gotoOtherSection(belongId)
{
	if(belongId == "1"){
		window.location.href = "search_complexation.jsp";
	}else if(belongId == "2"){
		//window.location.href = "complex_hostrecommend.jsp";
		window.location.href = "search_host.jsp";
	}else if (belongId == "3"){
		window.location.href = "search_guest.jsp";
		
	}else if(belongId == "4"){
		window.location.href = "search_monosaccharide.jsp";
	}else if(belongId == "5"){
		window.location.href = "complex_hostrecommend.jsp";
	}
}
function gotoInsertHostNavi(){
	
		window.location.href = "insert_host_new.jsp";
	
}
function gotoInsertGuestNavi(){
	
		window.location.href = "insert_guest.jsp?";
	
}
function gotoMonoNavi(belongId){
	if(belongId == "1"){
		window.location.href = "search_monosaccharide.jsp";
	}else if(belongId == "2"){
		window.location.href =  "insert_monosaccharide.jsp";
	}
}
function gotoCompNavi(belongId){
	if(belongId == "1"){
		window.location.href = "search_complexation.jsp";
	}else if(belongId == "2"){
		window.location.href =  "insert_complexation.jsp";
	}
}
function changePage(belongId){
	if(belongId == "1"){
		window.location.href ="search_complexation.jsp";
	}else if(belongId == "2"){
		window.location.href =  "insert_complexation.jsp";
	}else if (belongId == "3"){
		window.location.href =  "search_host.jsp";
		
	}else if(belongId == "4"){
		window.location.href =  "insert_host.jsp";
	}else if(belongId == "5"){
		window.location.href ="search_guest.jsp";
	}else if(belongId == "6"){
		window.location.href =  "insert_guest.jsp";
	}else if (belongId == "7"){
		window.location.href =  "search_monosaccharide.jsp";
		
	}else if(belongId == "8"){
		window.location.href =  "insert_monosaccharide.jsp";
	}
}
function gotoComplexationDetail(complexation){
	window.location.href =  "detail_complexation.jsp?complexation=" + complexation;
}
function gotoComplexationNavi(complexation){
	window.location.href =  "navi_complexation.jsp?complexation=" + complexation;
}
function gotoGuestDetail(basepath, guest){
	window.location.href =  "detail_guest.jsp?guest=" + guest;
}
function gotoHostDetail(basepath, host){
	window.location.href =  "detail_host.jsp?host=" + host;
}
function gotoMonoDetail(basepath, mono){
	window.location.href =  "detail_monosaccharide.jsp?mono=" + mono;
}
function link_to_searchHost(basePath) {
	window.location.href = basePath + "dist/example/search_host.jsp";
}
function link_to_insertHost(basePath) {
	window.location.href = basePath + "dist/example/insert_host_new.jsp";
}
function link_to_searchComplexation(basePath) {
	window.location.href = basePath + "dist/example/search_complexation.jsp";
}
function link_to_insertComplexation(basePath) {
	window.location.href = basePath + "dist/example/insert_complexation.jsp";
}
function link_to_searchGuest(basePath) {
	window.location.href = basePath + "dist/example/search_guest.jsp";
}
function link_to_insertGuest(basePath) {
	window.location.href = basePath + "dist/example/insert_guest.jsp";
}
function link_to_searchMono(basePath) {
	window.location.href = basePath + "dist/example/search_monosaccharide.jsp";
}
function link_to_insertMono(basePath) {
	window.location.href = basePath + "dist/example/insert_monosaccharide.jsp";
}

