# 1. Spring Data Jpa 와 같이 인터페이스만으로 API 가 가능한 모듈 개발
## 1.1. 기본 인터페이스 기능
* 기본적으로 제공되는 인터페이스를 상속해서 모든 API를 사용함
* SomeInterface extends MobileSender
  
## 1.2. 커스텀 인터페이스 기능
* 기본 인터페이스 외 별도의 API를 사용하고자 할 때 추가 선언하여 사용함
* SomeInterface extends MobileSender, CustomInterface

## 1.3. AOP 기술을 최대한 활용함
* 기본 인터페이스 외 별도의 API를 사용하고자 할 때 사용함
