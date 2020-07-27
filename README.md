# DGoodPrice [DGP]
대구시 D-DataHub의 착한가격업소 OpenAPI를 활용한 데이터 제공 및 커뮤니티 서비스 제작 프로젝트

## 활용 데이터
[D 데이터허브 - 대구시 착한가격업소 현황]
- http://data.daegu.go.kr/open/data/dataView.do?dataSetId=15055836&dataSetDetailId=150558362d86b0e867cf1&provdMethod=FILE

## 제작 과정
- 제작 기간
  - 총 제작기간 : 2020. 07. 09 ~ 2020. 07. 16
  
  - 기획 및 설계 : 2020. 07. 09 ~ 2020. 07. 10 (2일)
  - 앱 개발 : 2020. 07. 13 ~ 2020. 07. 15 (3일)
  - 최적화 및 소스코드 정리 : 2020. 07. 16
  - 데모데이 / 발표 : 2020. 07. 17

- 제작 인원
  - 박현정 : UI 설계 및 앱 개발
  - 정성윤 : 기획, 문서 작성 및 디자인 설계
  
- 프로젝트 요약
  - XML Parser를 이용하여 XML 파일 형태의 데이터 파싱
  - Google Map API로 업소 위치를 지도형태로 시각화
  - Google Map에서 사용되는 위치값(위도/경도)값으로 주소형식을 변경하기 위해 Geocoding
  - Firebase의 Realtime Database를 사용하여 게시판 구현

## 결과물 영상
[DGP 애플리케이션]
- https://www.youtube.com/watch?v=HNxYhVkpwg0&t=1s
