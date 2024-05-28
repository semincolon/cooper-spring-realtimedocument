# cooper-real-time-document
➤ Real-time document like the 'Notion'

***  
### Vue.js와 Spring Boot 연동하기
* 이 작업은 cooper-vue-frontend 레포의 커밋 버전 '513'에서 시작한 작업임  
* Spring의 src 폴더 내부에 vue 폴더가 위치하고 있으며 이는 변경될 수 있음  
* Vue 앱의 포트는 3000번으로 하였고, Spring 서버는 8000번으로 하였음  
* src/vue/test 폴더에서 npm run serve 명령어를 통해 Vue를 실행하고, Spring은 IDE에서 실행하면 됨  
* localhost:3000으로 접속하면 테스트할 수 있음

***  
### Next Goal  
* 유저 식별을 위해 토큰 정보를 사용해야 함  
* 아직 구현이 되지 않은 프론트엔드 부분이 완료되면 아래 기능을 추가해야 함  
  * 특정 블록에서 엔터키를 입력하여 새로운 블록을 삽입하면 DB에서도 그 순서에 맞게 블록이 추가되어야 함  
  * 백스페이스키를 입력하여 기존 블록을 삭제하는 것도 처리해야 함  
  * 한 사용자가 블록을 편집 중이라면 다른 사용자는 블록을 편집할 수 없도록 잠금을 설정해야 함