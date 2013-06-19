(function(d, c){

	// functions
	var getMeta = function (property, propertyValue, value) {	
		var metaTags = d.getElementsByTagName("meta");
		var result;
		
		for (var i = 0; i < metaTags.length; i++) {
			var p = metaTags[i].getAttribute(property);
			if (p == propertyValue) {
				result = metaTags[i].getAttribute(value);
			}
		}
		
		return result;
	}
	
	// ajax method
	var ajaxRequest = function (url, method, data, callback) {
		var xhttp = false;
		
		// ff, safari
		if(window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		// ie
		} else if (window.ActiveXObject) {
			xhttp = new ActiveXObject();
		}
		
		if(xhttp != false) {
			// Open Http Request connection
			xmlHttpRequst.open(method, url, true);
			// Set request header (optional if GET method is used)
			xmlHttpRequst.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			// Callback when ReadyState is changed.
			xmlHttpRequst.onreadystatechange = function()
			{
				if (xmlHttpRequst.readyState == 4)
				{
					if(typeof callback == "function") {
						callback();
					}
					
					return xmlHttpRequst.responseText;
				}
			}
			xmlHttpRequst.send(data);
		}
	}

	// get div container
	var container = d.getElementById(c);
	container.setAttribute("style", "display: inline-block;");
	
	if(!container) return;
	
	// define some vars
	var link, icon, iframediv, iframe;
	
	// link
	link = d.createElement("a");
	link.href = "javascript:void(0);";
	
	// icon
	icon = d.createElement("img");
	icon.src = "http://localhost:9000/public/images/heart_icon.png";
	
	// iframediv
	iframediv = d.createElement("div");
	iframediv.setAttribute("id", "iframediv");
	iframediv.setAttribute("style", "display: none;");
	iframediv.hide = false;
	
	// place icon into container
	link.appendChild(icon);
	container.appendChild(link);
	container.appendChild(iframediv);
	
	// get product meta
	var productImage = encodeURI(getMeta("name", "fb-product-image", "content")),
		productDescription = encodeURI(getMeta("name", "fb-product-description", "content")),
		productPrice = encodeURI(getMeta("name", "fb-product-price", "content")),
		productName = encodeURI(getMeta("name", "fb-product-name", "content")),
                productSubName = encodeURI(getMeta("name", "fb-product-subname", "content")),
                productBrand = encodeURI(getMeta("name", "fb-product-brand", "content")),
                productArtNr = encodeURI(getMeta("name", "fb-product-artNr", "content")),
		productURL = window.location;
		
	// link click event handling
	link.onclick = function (event) {	
		var cPositionX = event.pageX,
			cPositionY = event.pageY,
			screenX = event.screenX,
			screenY = event.screenY;
		
		//console.log(cPositionX, " - ", cPositionY, " - ", screenX, " - ", screenY, event);
		
		// show iframediv
		var x = cPositionX + 15,
			y = cPositionY + 15;
		
		// iframe not created yet
		if(typeof iframe == "undefined") {
			var link = "http://localhost:9000/request/add?pi=" + productImage + "&" + "pd=" + productDescription + "&" + "pp=" + productPrice + "&pn=" + productName + "&psn=" + productSubName + "&pb=" + productBrand + "&pu=" + productURL + "&partnr=" + productArtNr;
			iframe = d.createElement("iframe");
			iframe.frameBorder = 0;
			iframe.width = "300px";
			iframe.height = "200px";
			iframe.id = "fb-iframe";
			iframe.setAttribute("src", link);
			
			// apped iframe to iframediv
			iframediv.appendChild(iframe);
				
			iframediv.setAttribute("style", "display: inline-block; position: absolute; z-index: 999; border: 1px solid #CCC; padding: 5px; top: " + y + "px; left: " + x + "px;");
			
			// setup clickoutside event
			d.onclick = function () {
				if(iframediv.hide) {
					iframediv.setAttribute("style", "display: none;");
					iframediv.hide = false;
				} else {
					iframediv.hide = true;
				}
			};
		//	iframe set, show up
		} else {
			iframediv.setAttribute("style", "display: inline-block; position: absolute; z-index: 999; border: 1px solid #CCC; padding: 5px; top: " + y + "px; left: " + x + "px;");
		}
	};

}(document, "fb-root"));