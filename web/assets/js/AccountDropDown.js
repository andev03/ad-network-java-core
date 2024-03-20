// Lấy các element dropdown và dropdown options
const dropdown = document.querySelector('.dropdown');

const options = document.querySelector('.dropdown-options');

var searchbar = document.querySelector('input[type="search"]');

// Biến để theo dõi trạng thái click
let isClicked = false;


// Khi click vào dropdown thì đánh dấu là đã click 
dropdown.addEventListener('click', () => {
    isClicked = true;
}); 

// Khi click ra ngoài dropdown thì ẩn options
document.addEventListener('click', (e) => {
    if (!dropdown.contains(e.target)) {
        isClicked = false;
        options.style.display = 'none';
    }
});
