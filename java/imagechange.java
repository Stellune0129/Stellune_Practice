const profilePic = document.getElementById("profile-pic");
const profileUpload = document.getElementById("profile-upload");

// 클릭 → 파일 선택 창 열기
profilePic.addEventListener("click", () => {
  profileUpload.click();
});

// 새 파일 선택 → 이미지 교체
profileUpload.addEventListener("change", (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function(e) {
      profilePic.innerHTML = `
        <img src="${e.target.result}" alt="프로필 사진">
        <input type="file" id="profile-upload" accept="image/*" hidden>
      `;
      // 새로 삽입된 input을 다시 연결
      const newUpload = profilePic.querySelector("#profile-upload");
      newUpload.addEventListener("change", profileUploadChangeHandler);
    };
    reader.readAsDataURL(file);
  }
});

// 핸들러를 분리하면 재연결 용이
function profileUploadChangeHandler(event) {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function(e) {
      profilePic.innerHTML = `
        <img src="${e.target.result}" alt="프로필 사진">
        <input type="file" id="profile-upload" accept="image/*" hidden>
      `;
      const newUpload = profilePic.querySelector("#profile-upload");
      newUpload.addEventListener("change", profileUploadChangeHandler);
    };
    reader.readAsDataURL(file);
  }
}
