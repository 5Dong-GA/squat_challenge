# ⚠ Caution
전체 내용 수정중


# PoseNet
- pretrained model인 PoseNet 사용해서 Camera에 사람이 잡히는지 안잡히는지 확인

# Binary Image Classification 
- Squat 동작 인식하기 위해서 이미지로 Model 학습
- 앉은 상태(0) vs 서 있는 상태(1)
- 1 -> 0 -> 1 : Count 1개

# tflite converter
- Binary Image Classification model converting
- Keras로 학습시켜 .h5 확장자를 가지고 있는 모델에서 Android에서 구동시키기 위해서 tflite로 converting함

# FireBase
- Android에서 작동할 때 사용자들 정보 관리하는 Backend
- RealTime DataBase 사용하여 실시간으로 정보 갱신해야하는 특성 살림

# WebRTC
- 두 명이서 운동 같이할 때 같이 하는 사람의 영상을 아래 작은 칸에 실시간으로 ZOOM처럼 띄어주기 위해서 사용
- 실시간으로 영상을 보내는 것 뿐만 아니라 자신의 영상에 대해서 Binary Image Classification Model 사용해야하기 때문에 애를 많이 먹음
