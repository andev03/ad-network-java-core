/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global axios */

var province = document.getElementById("province");
  var city = document.getElementById("city");
  var district = document.getElementById("district");
  
  // Đường dẫn tới các Controller hoặc Servlet trên server của bạn
  var provinceEndpoint = "http://yourdomain.com/ProvinceController";
  var cityEndpoint = "http://yourdomain.com/CityController";
  var districtEndpoint = "http://yourdomain.com/DistrictController";
  
  // Gửi yêu cầu AJAX để lấy thông tin Province từ server
  axios.get(provinceEndpoint)
    .then(function (response) {
      renderCity(response.data);
    })
    .catch(function (error) {
      console.log(error);
    });

  function renderCity(data) {
    for (const x of data) {
      citis.options[citis.options.length] = new Option(x.Name, x.Id);
    }
    citis.onchange = function () {
      district.length = 1;
      ward.length = 1;
      if(this.value != ""){
        const result = data.filter(n => n.Id === this.value);

        for (const k of result[0].Districts) {
          district.options[district.options.length] = new Option(k.Name, k.Id);
        }
      }
    };
    district.onchange = function () {
      ward.length = 1;
      // Gửi yêu cầu AJAX để lấy thông tin District dựa trên Province và City đã chọn
      axios.get(districtEndpoint, {
          params: { provinceId: citis.value, cityId: district.value }
        })
        .then(function (response) {
          // Xử lý dữ liệu từ server và cập nhật dropdown Ward
        })
        .catch(function (error) {
          console.log(error);
        });
    };
  }


