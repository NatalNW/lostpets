const url_gdrive = "https://drive.google.com/open?id=";

document.onresize = function(){
  map.getViewPort().resize();
  map2.getViewPort().resize();
};

window.onload = function(){
  requestUsuarioSession();
  requestLostPets();
  alterView();
  
  let view = document.getElementById("modal");

	if(view.classList.contains("popup-on")){
    view.classList.remove("popup-on");
    view.classList.add("popup-off");
  }


	if(localStorage["color_theme"] == null){
		localStorage.setItem("color_theme", "0");
	}else{
		
		if(localStorage["color_theme"] == "0"){
			localStorage.setItem("color_theme", "1");
		}else{
			localStorage.setItem("color_theme", "0");
		}
		changeModel();
	}

}

window.addEventListener('resize', function () {
  map.getViewPort().resize(); 
  map2.getViewPort().resize(); 
});

function viewQuadros(){

	let view = document.getElementById("quadros");

	if(view.className.includes("area_listagem_on")){
		view.className = "display-flex area_listagem_off";
		document.getElementById("viewRanking").className = "ranking_off";
		document.getElementById("viewProfile").className = "profile_off";
	}else{
		view.className = "display-flex area_listagem_on";
		document.getElementById("viewRanking").className = "ranking_off";
		document.getElementById("viewProfile").className = "profile_off";
	}
}

function viewPerfil(){

	let view = document.getElementById("viewProfile");

	if(view.className.includes("profile_on")){
		view.className = "profile_off";
	}else{
    view.className = "profile_on";
    document.getElementById("viewRanking").className = "ranking_off";
	}
}

function viewRanking(){

	let view = document.getElementById("viewRanking");

	if(view.className.includes("ranking_on")){
		view.className = "ranking_off";
	}else{
    view.className = "ranking_on";
    document.getElementById("viewProfile").className = "profile_off";
    requestPontosUsuario();
	}
}

function addRanking(response, pos){
  let ranking = $("#ranking-add");
  ranking.append("<tr>"
  +"<td>"+pos+"</td>"
  +"<td>"+response.nomeUsuario+"</td>"
  +"<td>"+response.pontos+"</td>"
  +"<td>"+response.quantidadePetsAchados+"</td>"
  +"</tr>");
}

function viewMap(){
	
	let view = document.getElementById("map_area");

	if(view.className == "area_listagem_on"){
		view.className = "area_listagem_off";
	}else{
    view.className = "area_listagem_on";
    map.getViewPort().resize();
	}
}

var btn_view = document.getElementById("btn_abrir_nova_view");

function alterView(){
  if(btn_view.textContent == "map"){
    btn_view.textContent = "list";
    viewQuadros();
    viewMap();
  }else{
    btn_view.textContent = "map";
    viewQuadros();
    viewMap();
  }
}

btn_view.onclick = function (){
  alterView();
};

function resizeMapInfo(){
  let viewMap = document.getElementById("map-view-info");

  if(viewMap.classList.contains("map-view-info-off")){
    viewMap.classList.remove("map-view-info-off");
    viewMap.classList.add("map-view-info-on");
  }else{
    viewMap.classList.remove("map-view-info-on");
    viewMap.classList.add("map-view-info-off");
  }

}

function openModal(id){
  let view = document.getElementById("modal");

	if(view.classList.contains("popup-off")){
    document.getElementById("listagem-pets").classList.add("fechar-tudo");
    view.classList.remove("popup-off");
    view.classList.add("popup-on");
    resizeMapInfo();
      $.ajax({
        type: 'GET',
        dataType: 'json',
        headers: {
            'Content-Type': 'application/json'
        },
        url: url_resq+""+id,
        data: '',
        success: function (response) {
          document.getElementById("nome_animal_info_title").textContent = response.nomeAnimal;
          document.getElementById("tipo_animal_info_title").textContent = response.tipoAnimal;
          document.getElementById("nome_animal_info").textContent = response.nomeAnimal;
          document.getElementById("desaparecimento_animal_info").textContent = response.dataPerdido;
          document.getElementById("regiao_animal_info").textContent = "aaa";
          document.getElementById("foto-pet-perdido").src = url_gdrive+response.pathImg;
          document.getElementById("id-animal-hidden").value = id;
          
          addLocalizacaoPetInfo(url_gdrive+response.pathImg, response.latitude, response.longitude);

        },
        error: function () {
        }
      });  
    

	}else{
    document.getElementById("listagem-pets").classList.remove("fechar-tudo");
    resizeMapInfo();
		view.classList.remove("popup-on");
    view.classList.add("popup-off");   
	}
}

document.getElementById("btn-achei-pet").onclick = function(){
	requestAcheiPetPerdido(document.getElementById("id-animal-hidden").value);
	

}

//----------------------------------------------------------------------

var row_menu = document.getElementsByClassName("nav-wrapper");
var botoes = document.getElementsByClassName("btn-open-map");
var card_lost = document.getElementsByClassName("card-lost");
var desc_pet = document.getElementsByClassName("desc_pet");
var desc_info_pet = document.getElementsByClassName("desc_info_pet");
var cor0 = "white";
var cor1 = "black";
var cor0Card = "#6e83d6";

function changeModel(){
	if (localStorage["color_theme"] == "0"){
		document.getElementById("icon").innerHTML = "invert_colors_on";
		row_menu[0].style.backgroundColor = "red";
		for (var i = 0; i < botoes.length; i++) {
			botoes[i].style.backgroundColor = "red";
			
		}
		for (var i = 0; i < card_lost.length; i++) {
			card_lost[i].style.backgroundColor = "red";
		}
		
		for (var i = 0; i < desc_pet.length; i++) {
			desc_pet[i].style.color = cor0;
			desc_info_pet[i].style.color = cor0;
		}
			
		document.getElementById("quadros").style.backgroundColor = "black";
		
		localStorage.setItem("color_theme", "1");
		
	}else if (localStorage["color_theme"] == "1"){
		document.getElementById("icon").innerHTML = "invert_colors_off";
		row_menu[0].style.backgroundColor = cor0Card;
		for (var i = 0; i < botoes.length; i++) {
			botoes[i].style.backgroundColor = cor0Card;
			
		}
		for (var i = 0; i < card_lost.length; i++) {
			card_lost[i].style.backgroundColor = cor0Card;
		}
		for (var i = 0; i < desc_pet.length; i++) {
			desc_pet[i].style.color = cor1;
			desc_info_pet[i].style.color = cor1;
		}
			
		document.getElementById("quadros").style.backgroundColor = "white";
		
		localStorage.setItem("color_theme", "0");
	}	
}
//----------------------------------------------------------------------
 
















//MAPA

//Step 1: initialize communication with the platform
var platform = new H.service.Platform({
  app_id: 'YxULymX19IjsS2pE7KGo',//isso
  app_code: '6isWeBIxu4YmK1hfYF6s1w',// e isso tu pega no dev.heremaps
  useHTTPS: true
});
var pixelRatio = window.devicePixelRatio || 1;
var defaultLayers = platform.createDefaultLayers({
  tileSize: pixelRatio === 1 ? 256 : 512,
  ppi: pixelRatio === 1 ? undefined : 320
});

//Step 2: initialize a map  - not specificing a location will give a whole world view.
var map = new H.Map(document.getElementById('mapContainer'),
  defaultLayers.normal.map, {pixelRatio: pixelRatio});

//Step 3: make the map interactive
// MapEvents enables the event system
// Behavior implements default interactions for pan/zoom (also on mobile touch environments)
var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

// Create the default UI components
var ui = H.ui.UI.createDefault(map, defaultLayers);

function moveMap(map){
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(loc){
			map.setCenter({lat:loc.coords.latitude, lng:loc.coords.longitude });
			var loc = new H.map.Marker({lat:loc.coords.latitude, lng:loc.coords.longitude });
		    map.addObject(loc);
		});
	}else{
		map.setCenter({lat:-23.5766, lng:-46.4098 });
	}
    map.setZoom(14);
}

map.addEventListener('mapviewchange', function () {  
  if(map.getZoom() < 3) {
    map.setZoom(3);
  }
});

	function showPosition(position) {
		position.coords.latitude + 
	  "<br>Longitude: " + position.coords.longitude;
	}

function addLocalizacaoPet(icon_url, latitude, longitude){
  var icon = new H.map.Icon(icon_url,{size: {w: 30, h: 30}});
  var marker = new H.map.Marker({ lat:latitude, lng:longitude }, { icon: icon });
  map.addObject(marker);
  map.getViewPort().resize();
}


function carregarListaMapa(listPet){
  
  let div = document.getElementById("quadros");
  div.innerHTML = "";
  
  listPet.forEach(pet => {
    div.innerHTML += "<div class='card-lost dp-f'>"
    +"<div class='area_foto'>"
    +"<img src='"+url_gdrive+pet.pathImg+"' class='foto_pet'>"
    +"</div>"
    +"<div>"
    +"<table>"
    +"<tr><td class='desc_pet'>nome</td></tr>"
    +"<tr>"
    +"<td class='desc_info_pet'>"+pet.nomeAnimal+"</td>"
    +"</tr>"
    +"<tr>"
    +"<td class='desc_pet'>desaparecimento</td>"
    +"</tr>"
    +"<tr>"
    +"<td class='desc_info_pet'>"+pet.dataPerdido+"</td>"
    +"</tr>"
    +"<tr>"
    +"<td class='desc_pet'>região</td>"
    +"</tr>"
    +"<tr>"
    +"<td class='desc_info_pet'>itaquera-sp</td>"
    +"</tr>"
    +"</table>"
    +"</div>"
    +"<div class='btn_info' onclick='openModal("+pet.idAnimal+")'>+</div>"
    +"</div>";

    addLocalizacaoPet(url_gdrive+pet.pathImg, pet.latitude, pet.longitude);
  });
  moveMap(map);
}


//map 2

//Step 2: initialize a map  - not specificing a location will give a whole world view.
var map2 = new H.Map(document.getElementById('mapContainerView'),
  defaultLayers.normal.map, {pixelRatio: pixelRatio});


  map2.addEventListener('mapviewchange', function () {  
    if(map2.getZoom() < 3) {
      map2.setZoom(3);
    }
  });
  

//Step 3: make the map interactive
// MapEvents enables the event system
// Behavior implements default interactions for pan/zoom (also on mobile touch environments)
var behavior2 = new H.mapevents.Behavior(new H.mapevents.MapEvents(map2));

// Create the default UI components
var ui2 = H.ui.UI.createDefault(map2, defaultLayers);

function moveMapInfo(latitude, longitude){
  console.log(latitude+" ; "+longitude);
		map2.setCenter({lat: latitude, lng: longitude });
	  map2.setZoom(20);
}

function addLocalizacaoPetInfo(icon_url, latitude, longitude){
  //var icon2 = new H.map.Icon(icon_url,{size: {w: 30, h: 30}});
  var marker2 = new H.map.Marker({ lat:latitude, lng:longitude } );//, { icon: icon2 });
  map2.addObject(marker2);
  map2.getViewPort().resize();
  moveMapInfo(latitude, longitude);
}







