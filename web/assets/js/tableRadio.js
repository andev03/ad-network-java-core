/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Lắng nghe sự kiện thay đổi trạng thái
document.querySelectorAll('input[name="status"]').forEach(function(radio) { //tìm tất cả phần tử có tên là status, sau đó duyệt qua từng radio
    radio.addEventListener('change', function() {   ;//hàm lắng nghe sự kiện nếu có ai đó change trên radio
        // Lấy ra select box trong cùng một hàng
        //do có nhiều select box nên phải dùng cách gọi này 
        //select box tìm phần tử gần nhất có class .mb3 đại diện cho hàng hiện tại
        //sau đó tìm kiếm box kế tiếp
        //á đù thấy select rồi
        var selectBox = this.closest('.mb-3').nextElementSibling.querySelector('select');

        // Kiểm tra nếu radio button "Block" được chọn thì enable select box
        if (this.classList.contains('status-block') && this.checked) {
            selectBox.disabled = false;
        } else if (this.classList.contains('status-unblock') && this.checked) {
            // Ngược lại, disable select box
            selectBox.disabled = true;
        }
    });
});

//cập nhật sự kiện dựa trên trạng thái check hay không check của radio button
document.querySelectorAll('input[name="status"]:checked').forEach(function(radio) {    //tìm tất cả các radio button được checked
    var event = new Event('change');
    radio.dispatchEvent(event); //gửi sự kiện đến radio button
});