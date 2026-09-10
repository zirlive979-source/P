const $=id=>document.getElementById(id);
let mode="online";
document.querySelectorAll(".mode").forEach(btn=>btn.onclick=()=>{
  document.querySelectorAll(".mode").forEach(x=>x.classList.remove("active"));
  btn.classList.add("active"); mode=btn.dataset.mode;
  $("onlineBox").classList.toggle("hidden",mode!=="online");
  $("offlineBox").classList.toggle("hidden",mode!=="offline");
});
$("builderForm").onsubmit=async e=>{
  e.preventDefault();
  const status=$("status"); status.classList.remove("hidden");
  $("state").textContent="VALIDATING"; $("bar").style.width="15%";
  const data={mode,url:$("url").value.trim(),appName:$("appName").value.trim(),
    packageName:$("packageName").value.trim(),versionName:$("versionName").value.trim(),
    orientation:$("orientation").value,jsEnabled:$("jsEnabled").checked,
    domStorage:$("domStorage").checked,downloads:$("downloads").checked};
  if(mode==="online"&&!/^https?:\/\/.+/i.test(data.url)){alert("URL tidak valid.");return}
  if(mode==="offline"&&!$("zip").files[0]){alert("Pilih ZIP website.");return}
  $("state").textContent="READY"; $("bar").style.width="100%";
  $("log").textContent="Konfigurasi siap.\n\nV1 mendukung build nyata melalui GitHub Actions.\nUntuk one-click dari browser, hubungkan API backend yang aman ke workflow.\n\nJangan pernah menaruh GitHub token di index.html.";
};