# Alliebe🌿

## JIRA를 활용한 이슈기반협업 기초🦖

JIRA 무료버전으로는 Github Issues와 JIRA 실시간동기화가 불가능한 것으로 보입니다.   
따라서 JIRA에서 먼저 이슈를 생성해놓고, 작업 후 깃허브로 커밋을 하는 과정에서 커밋 제목에 OLIBE-<숫자> 형태의 이슈번호를 기록함으로써 Github -> JIRA 연동이 가능합니다. 

1. JIRA에 이슈 생성
2. 이슈번호 : OLIBE-<숫자>
3. 로컬에서 Master Branch로 Checkout   <br/>$ git checkout master
4. 중앙 저장소 Devolibe가 upstream 리모트 저장소로 등록됐는지 확인   <br/>$ git remote -v   <br/>안 되어있다면, $ git remote add upstream https://github.com/Devolibe/alliebe.git
5. 중앙 저장소 Devolibe에서 변경 사항 갱신   <br/>$ git pull upstream <Devolibe Branch 명> 현재는 Devolibe feature 브랜치가 디폴트
6. 새 Branch 생성   <br/>$ git branch <이슈 번호>-<이슈 내용>으로 Branch 생성 <br/>ex) $ git branch OLIBE-1-Test
7. 생성된 Branch로 이동 <br/>$ git checkout <새로 생성한 브랜치명>
8. 작업
9. 변경 사항 add   <br/>$ git add . (전체 파일을 추가할 경우)
10. 메세지와 함께 Commit  <br/><br/>$ git commit -m"OLIBE-이슈번호 명령조의 커밋 제목   <br/>>(커밋 제목과 내용 사이 줄 한칸 띄우기)  <br/>커밋 내용"    <br/><br/>ex)$ git commit -m"OLIBE-43 modify the readme.md       <br/>⚠ **커밋 제목 뒤에 \" 붙이면 안 됨**<br/>You can check a basic for the cooperation."<br/>
11.  개인 리모트 저장소로 Push <br/> $ git push origin <로컬에서 작업한 브랜치 명>
12.  개인 -> 중앙 Pr
13.  PR 과정에도 OLIBE-<숫자> 형태로 JIRA에서 발급한 이슈 번호 기록
14.  코드 리뷰 후 Merge
15.  JIRA에서 이슈 처리(IN PROGRESS -> DONE)
