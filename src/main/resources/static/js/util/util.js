function openAlert(id) {
	$(id).modal('open');
}

function hideAlert(id) {
	$(id).modal('close');
}

function setFocus(form, id) {
	document.forms[form].elements[id].focus();
}

function logout() {
	localStorage.setItem("clienteToken", null);
	window.location.href = "/login";
}