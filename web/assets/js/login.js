const signUpButton = document.getElementById('signInEmp');
const signInButton = document.getElementById('signInCus');
const registrationButton = document.getElementById('regisCus');
const logInButton = document.getElementById('logInCus');
const displayRegis = document.getElementById('regisForm');
const container = document.getElementById('container');

//Nhấn nút For Employee ở banner sẽ thực hiện các hiệu ứng của overlay-X
signUpButton.addEventListener('click', () => {
  container.classList.add("X-panel-active");
});

//Nhấn nút For Customer ở banner sẽ dừng thực hiện các hiệu ứng của overlay-X và trở về như cũ (vẫn có animation)
signInButton.addEventListener('click', () => {
  container.classList.remove("X-panel-active");
});


//Nhấn anchor đăng kí Registration sẽ cho hiện ra Phần Registration
registrationButton.addEventListener('click', function (event) {
  event.preventDefault();
  const currentDisplay = window.getComputedStyle(displayRegis).opacity; //Lấy opacity của Registration (mặc định là 0)
  if (currentDisplay === '0') { //nếu bằng 0 thì đổi thành 1
    displayRegis.style.opacity = '1';
    container.classList.add("Y-panel-active");    //thực hiện hành động của Y-panel-active
    logInButton.addEventListener('click', function (event) {      //Nhấn nút sign in thì sẽ chuyển Registration Form opacity từ 1 thành 0
      event.preventDefault();    //Ngăn hành động mặc định, trường hợp này ngăn làm mới trang hoặc sang trang mới
      displayRegis.style.opacity = '0';
      container.classList.remove("Y-panel-active");    //dừng hành động
    });
  }
});
