# Alliebe

## 이슈기반협업 편의를 위한 Git 기초
1. Master Branch로 Checkout   <br/>$ git checkout master
2. 중앙 저장소 Devolibe가 리모트 저장소로 등록됐는지 확인   <br/>$ git remote -v   <br/>안 되어있다면, $ git remote add upstream https://github.com/Devolibe/alliebe.git
3. 중앙 저장소 Devolibe에서 변경 사항 갱신   <br/>$ git pull upstream <Devolibe Branch 명> 현재는 Devolibe feature 브랜치가 디폴트
4. 새 Branch 생성   <br/>$ git branch <이슈 번호>-<이슈 내용>으로 Branch 생성 <br/>ex) $ git branch 4-Story
5. 변경 사항 add   <br/>$ git add . (전체 파일을 추가할 경우)
6. 메세지와 함께 Commit  <br/><br/>$ git commit -m"Close #이슈번호 명령조의 커밋 제목   <br/>>(커밋 제목과 내용 사이 줄 한칸 띄우기)  <br/>"커밋 내용"    <br/><br/>ex)$ git commit -m"Close #9 Refactor the ExampleActivity       <br/><br/>The ExampleActivity has been refactored as discussed at the latest meeting."<br/><br/>**⚠ 커밋 제목 뒤에 \" 붙이면 안 됨**
8.  개인 리모트 저장소로 Push
9.  
