# Email-android

##### 2019-04-01

- [x] SMTP 메일 보내기 구현

<br/>

##### 2019-04-05

- [x] imap 메일 받아오기
- [x] 화면 따로 분리

>e.printStackTrace()는 log error로 나오지 않음..ㅠ
>
>로딩할 때 너무 오래걸림..

<br/>

##### 2019-04-06

- [ ] 제목, 내용 글자 수 제한 걸어야 됨 -> 날짜가 안보임..

  주소 한글 이름 다 깨져서 받아짐

  from에서 주소 추출해서 빼야됨..
- [ ] 사진만 있는 메일일 경우에는…? 미리보기 x 

  버튼 아래로 옮기기

> 정말 너무 너무 느림ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
>
> 10개씩 받아오는 걸로 줄였는데도ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
>
> 500개 받아오는데 10분 말이 되냐..

<br/>

##### 2019-04-07

- [x] date format
- [x] 한글 인코딩-> api상에서는 Content만 되어있음… 언젠가 풀리퀘 던져줘야지..
- [x] 버튼 아래로 옮기기

<br/>

##### 2019-04-10

- [x] 사진 안나오는 것
- [ ] onLoad 고치기 -> Async가 1번만 호출되야됨 -> 인스턴스 새로 생성 / Readmail에서 메일 total 수 받아와야됨..
- [ ] 느린 것 해결……(가장 우선)
- [x] 메일 보내는 부분 갑자기 에러ㅠ
- [x] 메일 상세페이지 구현

> 느린 것 원인 추정
>
> - 메일 본문은 2가지 타입이 있음(text, multipart) -> text는 그냥 받는데 multipart는 한 줄씩 받아옴(근데 이게 html, css 포함이라 엄청 길다..) 아무 처리 안하고 메일 하나 받아오는데 걸리는 시간은 0.5~1초. 하지만 이 처리 부분이 들어가면 최소 1.5에서 크면 4초 넘음………… 문제는 이 처리 부분을 받으면서 해줘야 한다는 것(folder.open일 때)
>
>   -> gmail과 달리 naver, daum은 엄청.. 빠름..
>
> 느린 것 문제 해결법 가정
>
> 1. 받아오는 것을 MainActivity로 바꿔보기 -> 안됨. 웹서버접속시 Thread이용해야됨.. -> 더 오래걸림..
> 2. 이전 것을 내장에 저장했다가 맨 처음에 보여주고 뒤에서는 받아오면서 대조하기(근데 각 메일마다 key가 있어야될 것 같음..-> 없는 것 같은데 그러면 시간과 from으로 구분하면 될 것 같음)
>
> 메일 중복 문제 - 메일이 중복되어서 나온게 아니였음.. gmail에서 같은 날 같은 주소로 메일이 오면 묶어서 1통으로 보이는 거였음..

<br/>

##### 2019-04-14

- [x] 다운로드 열기(모든 파일 보도록)

> <https://stackoverflow.com/questions/17740976/send-email-with-attachment-from-internal-storage>
>
> 공유부분할 때 읽어보기

<br/>

##### 2019-04-15

