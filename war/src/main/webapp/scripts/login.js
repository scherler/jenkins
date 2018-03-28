const isIE11 = !!window.MSInputMethodContext && !!document.documentMode;
if (isIE11) {
    const headElement = document.getElementsByTagName('head')[0];
    const style = document.createElement('link');
    style.setAttribute('href',  headElement.getAttribute('data-resurl') + '/css/login.ie.css');
    style.setAttribute('rel', 'stylesheet');
    style.setAttribute('type', 'text/css');
    document.getElementsByTagName('head')[0].appendChild(style);
}