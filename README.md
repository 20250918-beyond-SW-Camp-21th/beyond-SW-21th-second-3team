### ☀️ Commit Convention

---

<details>
<summary style = " font-size:1.3em;">Rules</summary>
<div markdown="1">

### 1. Git Flow

작업 시작 시 선행되어야 할 작업은 다음과 같습니다.


> 1. issue를 생성합니다.
> 2. feature branch를 생성합니다.
> 3. add → commit → push → pull request 를 진행합니다.
> 4. pull request를 develop branch로 merge 합니다.
> 5. 이전에 merge된 작업이 있을 경우 다른 branch에서 진행하던 작업에 merge된 작업을 pull 받아옵니다.
> 6. 종료된 issue와 pull request의 label을 관리합니다.

### 2. IntelliJ

IntelliJ로 작업을 진행하는 경우, 작업 시작 시 선행되어야 할 작업은 다음과 같습니다.

> 1. 깃허브 프로젝트 저장소에서 issue를 생성합니다.
> 2. IntelliJ의 git 탭 → local develop branch 우클릭 → update 를 진행합니다.
> 3. IntelliJ의 git 탭 → local develop branch 우클릭 → new branch from ‘develop’ 을 진행합니다.
> 4. 생성한 issue 번호에 맞는 feature branch를 생성함과 동시에 feature branch로 checkout 합니다.
> 5. feature branch에서 issue 단위 작업을 진행합니다.
> 6. 작업 완료 후, add → commit을 진행합니다.
> 7. push 하기 전, remote develop branch의 변경 사항을 확인하기 위해 2번 과정을 다시 수행합니다.
> 8. IntelliJ의 git 탭 → local develop branch 우클릭 → merge ‘develop’ into ‘4번 과정에서 생성한 feature branch’ 를 진행합니다.
> 9. 만약 코드 충돌이 발생하였다면, IntelliJ에서 코드 충돌을 해결하고 add → commit을 진행합니다.
> 10. push → pull request (feature branch → develop branch) 를 진행합니다.
> 11. pull request가 작성되면 작성자 이외의 다른 팀원이 code review를 진행합니다.
> 12. 최소 한 명 이상의 팀원에게 code review와 approve를 받은 경우 pull request 생성자가 merge를 진행합니다.
> 13. 종료된 issue와 pull request의 label과 milestone을 관리합니다.


### 3. Etc

준수해야 할 규칙은 다음과 같습니다.

> 1. develop branch에서의 작업은 원칙적으로 금지합니다. 단, README 작성은 develop branch에서 수행합니다.
> 2. commit, push, merge, pull request 등 모든 작업은 오류 없이 정상적으로 실행되는 지 확인 후 수행합니다.

</div>
</details>

<details>
<summary style = " font-size:1.3em;">Branch</summary>
<div markdown="1">

### 1. Branch

branch는 작업 단위 & 기능 단위로 생성하며 이는 issue를 기반으로 합니다.

### 2. Branch Naming Rule

branch를 생성하기 전 issue를 먼저 작성합니다. issue 작성 후 생성되는 번호와 domain 명을 조합하여 branch의 이름을 결정합니다. `<Prefix>/<Issue_Number>-<Domain>` 의 양식을 준수합니다.

### 3. Prefix

- `main` : 개발이 완료된 산출물이 저장될 공간입니다.
- `develop`: feature branch에서 구현된 기능들이 merge될 default branch 입니다.
- `feature`: 기능을 개발하는 branch 입니다. 이슈 별 & 작업 별로 branch를 생성 후 기능을 개발하며 naming은 소문자를 사용합니다.

### 4. Domain

- `user`, `home`, `error`, `config` 


### 5. Etc

- `feature/7-user`, `feature/5-config`


</div>
</details>

<details>
<summary style = " font-size:1.3em;">Issue</summary>
<div markdown="1">

### 1. Issue

작업 시작 전 issue 생성이 선행되어야 합니다. issue 는 작업 단위 & 기능 단위로 생성하며 생성 후 표시되는 issue number 를 참조하여 branch 이름과 commit message를 작성합니다.

issue 제목에는 기능의 대표적인 설명을 적고 내용에는 세부적인 내용 및 작업 진행 상황을 작성합니다.

issue 생성 시 github 오른편의 assignee, label을 적용합니다. assignee는 해당 issue 담당자, label은 작업 내용을 추가합니다.

### 2. Issue Naming Rule

`[<Prefix>] <Description>` 의 양식을 준수하되, prefix는 commit message convention을 따릅니다.

### 3. Etc

<aside>
[feat] 약속 잡기 API 구현
<br/>[chore] spring data JPA 의존성 추가

</aside>

---

</div>
</details>

<details>
<summary style = " font-size:1.3em;">Commit</summary>
<div markdown="1">

### 1. Commit Message Convention

`[<Prefix>] #<Issue_Number> <Description>` 의 양식을 준수합니다.

- **feat** : 새로운 기능 구현 `[feat] #11 구글 로그인 API 기능 구현`
- **fix** : 코드 오류 수정 `[fix] #10 회원가입 비즈니스 로직 오류 수정`
- **del** : 쓸모없는 코드 삭제 `[del] #12 불필요한 import 제거`
- **docs** : README나 wiki 등의 문서 개정 `[docs] #14 리드미 수정`
- **refactor** : 내부 로직은 변경 하지 않고 기존의 코드를 개선하는 리팩터링 `[refactor] #15 코드 로직 개선`
- **chore** : 의존성 추가, yml 추가와 수정, 패키지 구조 변경, 파일 이동 `[chore] #21 yml 수정`, `[chore] #22 lombok 의존성 추가`
- **test**: 테스트 코드 작성, 수정 `[test] #20 로그인 API 테스트 코드 작성`
- **style** : 코드에 관련 없는 주석 달기, 줄바꿈

</div>
</details>

<details>
<summary style = " font-size:1.3em;">Pull Request</summary>
<div markdown="1">

### 1. Pull Request

develop & main branch로 merge할 때에는 pull request가 필요합니다. pull request의 내용에는 변경된 사항에 대한 설명을 명시합니다.

### 2. Pull Request Naming Rule

`[<Prefix>] <Description>` 의 양식을 준수하되, prefix는 commit message convention을 따릅니다.

### 3. Etc

[feat] 약속 잡기 API 구현
<br/>[chore] spring data JPA 의존성 추가

</div>
</details>
