const timer = document.querySelector("#timer");
t1 = setTimeout(()=>{
    timer.innerHTML="将在2秒后跳转回上传页面";
},1000);
t2 = setTimeout(()=>{
    timer.innerHTML="将在1秒后跳转回上传页面";
},2000);
t3 = setTimeout(()=>{
    window.location.href = '/';
},3000);
document.querySelector("#stop").addEventListener("click",()=> {
    console.log("clicked");
    clearTimeout(t1);
    clearTimeout(t2);
    clearTimeout(t3);
})