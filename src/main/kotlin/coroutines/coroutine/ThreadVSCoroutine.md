### 스레드 vs 코루틴

스레드
- 스레드는 독립된 스택 영역을 갖고 있으며, 힙 영역을 공유

코루틴
- 동일한 스레드에서 코루틴이 실행된다면, 메모리 전부를 공유하므로 스레드보다도 context switching 비용 적음
- 단 하나의 스레드만으로도 동시성을 확보 가능

<img width="886" height="428" alt="Image" src="https://github.com/user-attachments/assets/fb2ebd0c-53aa-4df4-8ec8-3b4f97e3e8b3" />