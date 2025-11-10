const profilePic = document.getElementById("profile-pic");
const profileUpload = document.getElementById("profile-upload");

// 초기 상태를 복원하는 함수
function resetProfile() {
  profilePic.innerHTML = `
    <span>프로필 사진</span>
    <input type="file" id="profile-upload" accept="image/*" hidden>
  `;
  const newUpload = profilePic.querySelector("#profile-upload");
  newUpload.addEventListener("change", profileUploadChangeHandler);
}

// 이미지 업로드 후 표시하는 함수
function profileUploadChangeHandler(event) {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function(e) {
      profilePic.innerHTML = `
        <img src="${e.target.result}" alt="프로필 사진">
        <input type="file" id="profile-upload" accept="image/*" hidden>
        <button class="remove-btn" id="remove-btn">×</button>
      `;
      // 새 input & 삭제 버튼 이벤트 재연결
      const newUpload = profilePic.querySelector("#profile-upload");
      const removeBtn = profilePic.querySelector("#remove-btn");
      newUpload.addEventListener("change", profileUploadChangeHandler);
      removeBtn.addEventListener("click", (e) => {
        e.stopPropagation(); // 프로필 클릭 이벤트 막기
        resetProfile();
      });
    };
    reader.readAsDataURL(file);
  }
}

// 프로필 영역 클릭 → 파일 선택
profilePic.addEventListener("click", () => {
  const uploadInput = profilePic.querySelector("#profile-upload");
  uploadInput.click();
});

// 최초 input 이벤트 연결
profileUpload.addEventListener("change", profileUploadChangeHandler);
