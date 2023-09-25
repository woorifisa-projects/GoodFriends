<p align="middle">
  <a href="https://hits.seeyoufarm.com"><img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fwoowacourse-teams%2F2022-goodfriends&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false"/></a>
</p>

<div align="center">
<img width ="500" src="https://github.com/woorifisa-projects/GoodFriends/assets/83820185/e4dd46f4-37a0-4c01-a0da-0dc1ad2274d4"/>
 <h2> 친구처럼 안전한 중고 거래 서비스, 굿프렌즈 🤹🏻‍♀️ </h2>

[![wiki](http://img.shields.io/badge/-GitHub%20WiKi-black?style=flat&logo=github&logoColor=white&link=https://github.com/woorifisa-projects/GoodFriends/wiki)](https://github.com/woorifisa-projects/GoodFriends/wiki)
[![Release](https://img.shields.io/github/v/release/woorifisa-projects/GoodFriends?color=blue)](https://github.com/woorifisa-projects/GoodFriends/releases/tag/v1.1.0)
[![tistory](http://img.shields.io/badge/-Tistory-3db39e?style=flat&logo=tistory&logoColor=white&link=https://goodfriends-team.tistory.com//)](https://goodfriends-team.tistory.com/)

</div>

## 🪧 서비스를 소개합니다.

![](/docs/service_introduction.png)

`굿프렌즈`는 **사기 피해 방지를 위한 안심 거래 서비스** 입니다.

비대면으로 인한 사기 피해를 예방하기 위해 `굿프렌즈`는 다음과 같은 2가지를 기준으로 웹 서비스를 기획했습니다.

1. 지역 커뮤니티 기반 **대면** 중심의 웹 플랫폼
2. 대면 거래에서 문제가 발생하지 않도록 **안심거래존**에서 거래를 주고 받는 컨셉

친구같이 편하게 거래하는 분위기를 연출하기 위한 UI/UX 기획하여 개발했습니다.



### 🙋🏻 서비스 대상자

1. 대학교를 다니면서 자취를 하고 있는 대학생.
2. 이제 막 사회를 다니고 있는 사회 초년생.
3. 집에서 안쓰는 물건을 중고로 판매하고 싶은 모든 사람들.

## 🏷 기능을 소개합니다.

> 해당 서비스에 대해 간단하게 보고 싶다면, [서비스 소개 링크](https://goodfriends.world/about)를 클릭해 주세요!

### 🧷 기능 1: `소셜 로그인` - 간편하게 로그인할 수 있어요.

<img width="800" alt="google_social_login" src="https://github.com/devFancy/GoodFriends/assets/83820185/a428fccb-9e48-4873-89ab-17b92d4bf2e2">

- 자주 사용하는 `구글` 계정을 통해 로그인/회원가입을 할 수 있어요.
- 한번 로그인하면 **최대 14일** 동안 로그인이 유지 가능해요.
- 구글로 가입하면, 구글로부터 이메일, 닉네임, 프로필 이미지가 프로필 정보에 자동 저장하게 돼요.
- 세션에 저장되는 쿠키를 보호하기 위해 **HTTP Only 적용 및 Set-Cookie를 설정**했어요.

### 🧷 기능 2: `상품` - 원하는 중고 상품을 판매 및 구매할 수 있어요.

<img  width="1200" alt="product_register" src="https://github.com/devFancy/GoodFriends/assets/83820185/786f8ab7-d1e4-4223-a128-bd9639721087">

- **프로필 정보를 입력**해야만 상품을 구매 또는 판매할 수 있도록 설정했어요.
- 본인이 판매/구매하고 싶은 상품을 **안심 거래 프로세스**를 통해 등록하거나 주문할 수 있어요.
- 현재 상품 이미지 크기는 **1M 미만**이어야만 추가할 수 있어요. (추후에 MB로도 추가할 수 있도록 개선할 예정이에요.)
- 상품을 등록할 때, 상품 이미지는 최대 10개, 자세한 설명은 최대 200글자 이내로 설정했어요.
- 판매자는 거래를 원하는 사람과 `거래하기` 버튼을 클릭하면, 해당 사람의 정보(이메일, 닉네임, 프로필 이미지)를 확인할 수 있어요.

### 🧷 기능 3: `신고` - 부적절한 게시글을 신고하여 추가 피해를 방지할 수 있어요.

<img width="1200" alt="board_declaration" src="https://github.com/devFancy/GoodFriends/assets/83820185/58258d9f-f5fc-4222-a1a7-ab8eb4cb1f5b">

- 상품 상세페이지에서 `신고하기`버튼을 통해 부적절한 게시글을 신고할 수 있어요.
- 신고 페이지에는 신고 카테고리와 신고 내용을 작성하여 `제출하기`버튼을 통해 신고 처리를 할 수 있어요.
- 누적 신고 횟수가 N번 이상이 된 유저는 `부정행위자`로 판단되어 해당 서비스를 일시적으로 제한할 수 있도록 설정해줬어요.(추후에 관리자가 신고 관리하도록 개선할 예정이에요.)

### 🧷 기능 4: `마이페이지` - 사용자의 프로필 정보, 구매 및 판매 목록을 확인할 수 있어요.

<img width="1200" alt="mypage" src="https://github.com/devFancy/GoodFriends/assets/83820185/659b726a-ecf7-4478-9c0d-2bc1522f19a5">

- 프로필 정보에 개인 정보(이메일, 닉네임, 프로필 이미지, 계좌 종류 및 계좌 번호, 휴대폰, 주소)를 저장할 수 있어요.
- 휴대폰 인증을 통해 중복 가입을 제한할 수 있어요.
- 본인의 구매/판매한 상품들을 조회할 수 있고 해당 상품을 클릭하면 상세 페이지로 이동할 수 있어요.
- 본인이 상품을 거래한 횟수 및 신고 당한 횟수를 확인할 수 있어요.

## Tech Stacks

### 👩🏻‍🎨 Front-end

![](https://github.com/woorifisa-projects/GoodFriends/assets/83820185/90a2d7ee-0587-49f8-9e08-635279ba1ba3)

### 🧑🏻‍🚀 Back-end

![](https://github.com/woorifisa-projects/GoodFriends/assets/83820185/a3970af5-704e-4b7a-8c70-f20ca7dd3438)

## ☁️ Infrastructure

![‎woorifisa-goodfriends ‎001](https://github.com/devFancy/GoodFriends/assets/83820185/f70f57ec-1e9d-4174-9b10-60740e7b00f5)

## ⛓ CI/CD pipeline

![](https://github.com/devFancy/GoodFriends/assets/83820185/a97e8b15-3045-47e5-8fc3-b11386b2319b)

## 🙌🏻 팀원

|                                              [🐕 영빈](https://github.com/yybeen)                                               |                                                       [🦅 준용](https://github.com/devFancy)                                                       |                                              [🐈 윤서](https://github.com/han0224)                                               |                                              [🦉 현민](https://github.com/Hmini0101)                                              |
| :-----------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------: |
| <a href="https://github.com/yybeen"> <img src="https://avatars.githubusercontent.com/u/59864345?v=4" width=200px alt="_"/> </a> |         <a href="https://github.com/devFancy"> <img src="https://avatars.githubusercontent.com/u/83820185?v=4" width=200px alt="_"/> </a>          | <a href="https://github.com/han0224"> <img src="https://avatars.githubusercontent.com/u/70616579?v=4" width=200px alt="_"/> </a> | <a href="https://github.com/hyewoncc"> <img src="https://avatars.githubusercontent.com/u/59523668?v=4" width=200px alt="_"/> </a> |
|                                                             백엔드                                                              |                                                                       백엔드                                                                       |                                                            프론트엔드                                                            |                                                            프론트엔드                                                             |
|                                                     팀원들이 보는 영빈이는                                                      |                                                               팀원들이 보는 준용이는                                                               |                                                       팀원들이 보는 윤서는                                                       |                                                      팀원들이 보는 현민이는                                                       |
|          :deciduous_tree: 묵묵히 자기일을 끝까지는 해내는 <br/> :star2: 숨겨지지 않는 밝은 에너지 <br/> 🧘🏻‍♀️ 멘탈 관리자          | ✨ 팀원을 잘 이끄는 리더상 <br/> :gun:어느 하나 놓치지 않는 예리하고 클래식한 <br/> :writing_hand: 프로젝트의 모든 것을 깔끔하게 정리해주는 정리왕 |                  🚀 믿고 보는 만능 해결사 <br/> 💊 영양 한가득 피드백 장인 <br/> 💥 꼼꼼하고 든든한 트러블 슈터                  |         💻 분석과 예리함을 갖추신 <br/> :fire: 항상 끝까지 최선을 다하는 <br/> :+1: 맡은 일을 끝까지 해내는 풀스택 개발자         |
