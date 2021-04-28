var sliderWrapper = document.getElementsByClassName('image-container'), //고정 div
    sliderContainer = document.getElementsByClassName('slider-container'), //슬라이드 이동 div
    slides = document.getElementsByClassName('slide'), //슬라이드
    sliderCount = slides.length,
    currentIdx = 0, //현재 슬라이드 index
    topHeight = 0, //가장 높은 슬라이드로 height 지정
    navPrev = document.getElementById('prev'), //슬라이드 왼쪽 이동 버튼
    navNext = document.getElementById('next'), //슬라이드 오른쪽 이동 버튼
    timer = undefined, //슬라이드 이동 인터벌
    pager = document.querySelector('.pager'), //슬라이드 하단 원형 페이저
    pagerHtml = ''; //페이저 index

function calculateTallestSlide() { //topHeight=가장 높은 슬라이드 값 
    for (var i = 0; i < sliderCount; i++) {
        if (slides[i].offsetHeight > topHeight) {
            topHeight = slides[i].offsetHeight;
        }
    }
    sliderWrapper[0].style.height = topHeight + 'px';
    sliderContainer[0].style.height = topHeight + 'px'; //컨테이너 높이=topHeight
}

calculateTallestSlide();

for (var i = 0; i < sliderCount; i++) {
    slides[i].style.left = i * 100 + '%'; //슬라이드 i+1개 가로로 붙임 left=0%, 100%, 200%...
    pagerHtml += '<span data-idx="' + i + '">' + (i + 1) + '</span>'; //슬라이드 개수만큼 Html연결
    pager.innerHTML = pagerHtml; //연결된 Html로 페이저 생성 
}

var pagerBtn = document.querySelectorAll('.pager span'); //위에서 생성된 페이저 전체 변수 선언

function goToSlide(idx) { //슬라이드 이동
    sliderContainer[0].classList.add('animated'); //슬라이드 컨테이너 애니메이션 적용
    sliderContainer[0].style.left = idx * -100 + '%'; //index따라 left = 0%, 100%, 200%...슬라이드 나타냄
    currentIdx = idx;
    for (var j = 0; j < pagerBtn.length; j++) {
        pagerBtn[j].classList.remove('active'); //페이저 전체 active class 삭제
    }
    pagerBtn[idx].classList.add('active'); //현재 index 페이저에 active class->색상 이펙트
}

goToSlide(0); //로딩 시 0번 index로 시작

navPrev.addEventListener('click', function (e) { //왼쪽 화살표 클릭시 이전 슬라이드로 이동
    if (currentIdx > 0) {
        goToSlide(currentIdx - 1);
    } else {
        goToSlide(sliderCount - 1); //currentIdx==0일시 가장 끝 index로 이동
    }
});

navNext.addEventListener('click', function (e) { //왼쪽 화살표 클릭시 다음 슬라이드로 이동
    if (currentIdx < sliderCount - 1) {
        goToSlide(currentIdx + 1);
    } else {
        goToSlide(0); //마지막 index일시 0번 index로 이동
    }
});

function startAutoSlide() { //자동슬라이드
    timer = setInterval(function () {
        var nextIdx = (currentIdx + 1) % sliderCount; //currentIdx=0,1,2 당 nextIdx=1,2,0 입력
        goToSlide(nextIdx); //다음 인덱스로 슬라이드 이동
    }, 3000); //3초마다 이동
}
startAutoSlide();

function stopAutoSlide() {
    clearInterval(timer); //자동슬라이드 인터벌 삭제
}

sliderWrapper[0].addEventListener('mouseover', function () {
    stopAutoSlide(); //컨테이너에 마우스 올릴 시 자동슬라이드 삭제
});

sliderWrapper[0].addEventListener('mouseout', function () {
    startAutoSlide(); //컨테이너에서 마우스 내릴 시 자동슬라이드 시작
});

for (var i = 0; i < pagerBtn.length; i++) {
    pagerBtn[i].addEventListener('click', function (e) { //페이저 클릭 이벤트
        var pagerNum = e.target.innerText - 1; //페이저의 innerText (i+1)에서 -1한 값=index
        goToSlide(pagerNum) //해당 페이저 index로 슬라이드 이동
    });
}