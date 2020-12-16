### WebRTCAndroid
This is an Android Studio reference project for WebRTC based video and voice calling app. (https://appr.tc)

<img src="screenshots/screenshot1.png" width=200/> <img src="screenshots/screenshot2.png" width=200/> <img src="screenshots/screenshot3.png" width=200/>

For downloading the latest source follow the instructions given [here](https://webrtc.org/native-code/android/). 

### Modifications

1. The code has been split into library and app modules for easy reference. 
2. Changes incorporated for requesting runtime permissions and targetSdkVersion updated to 27 (Oreo).
3. Added dependency to official prebuilt library available at JCenter 'org.webrtc:google-webrtc'.

### Miscellaneous

The app connects to AppRTC server hosted by Google. If you wish to deploy your own server then follow the instructions given [here](https://github.com/webrtc/apprtc). Or you may use out-of-the-box docker image for AppRTC-Server available [here](https://hub.docker.com/r/piasy/apprtc-server/).

Once your server is up and running, you will need to specify the server url in the Settings screen of the app. Update 'Room server URL' under Miscellaneous settings.

<img src="screenshots/screenshot4.png" width=200/>

### Pose Detection in the Browser: PoseNet Model

### Note: We've just released Version 2.0 with a **new ResNet** model and API. Check out the new documentation below.

This package contains a standalone model called PoseNet, as well as some demos, for running real-time pose estimation in the browser using TensorFlow.js.

[Try the demo here!](https://storage.googleapis.com/tfjs-models/demos/posenet/camera.html)

PoseNet can be used to estimate either a single pose or multiple poses, meaning there is a version of the algorithm that can detect only one person in an image/video and one version that can detect multiple persons in an image/video.

[Refer to this blog post](https://medium.com/tensorflow/real-time-human-pose-estimation-in-the-browser-with-tensorflow-js-7dd0bc881cd5) for a high-level description of PoseNet running on Tensorflow.js.

To keep track of issues we use the [tensorflow/tfjs](https://github.com/tensorflow/tfjs) Github repo.

## Documentation Note

>> The README you see here is for the [PoseNet 2.0 version](https://www.npmjs.com/package/@tensorflow-models/posenet). For README of the previous 1.0 version, please look at the [README published on NPM](https://www.npmjs.com/package/@tensorflow-models/posenet/v/1.0.3).

## Installation

You can use this as standalone es5 bundle like this:

```html
  <script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs"></script>
  <script src="https://cdn.jsdelivr.net/npm/@tensorflow-models/posenet"></script>
```

Or you can install it via npm for use in a TypeScript / ES6 project.

```sh
npm install @tensorflow-models/posenet
```

## Usage

Either a single pose or multiple poses can be estimated from an image.
Each methodology has its own algorithm and set of parameters.


### Loading a pre-trained PoseNet Model

In the first step of pose estimation, an image is fed through a pre-trained model.  PoseNet **comes with a few different versions of the model,** corresponding to variances of MobileNet v1 architecture and ResNet50 architecture. To get started, a model must be loaded from a checkpoint:

```javascript
const net = await posenet.load();
```

By default, `posenet.load()` loads a faster and smaller model that is based on MobileNetV1 architecture and has a lower accuracy. If you want to load the larger and more accurate model, specify the architecture explicitly in `posenet.load()` using a `ModelConfig` dictionary:


#### MobileNet (smaller, faster, less accurate)
```javascript
const net = await posenet.load({
  architecture: 'MobileNetV1',
  outputStride: 16,
  inputResolution: { width: 640, height: 480 },
  multiplier: 0.75
});
```

#### Config params in posenet.load()

 * **architecture** - Can be either `MobileNetV1` or `ResNet50`. It determines which PoseNet architecture to load.

 * **outputStride** - Can be one of `8`, `16`, `32` (Stride `16`, `32` are supported for the ResNet architecture and stride `8`, `16`, `32` are supported for the MobileNetV1 architecture. However if you are using stride `32` you must set the multiplier to `1.0`). It specifies the output stride of the PoseNet model. The smaller the value, the larger the output resolution, and more accurate the model at the cost of speed. Set this to a larger value to increase speed at the cost of accuracy.

* **inputResolution** - A `number` or an `Object` of type `{width: number, height: number}`. Defaults to `257.` It specifies the size the image is resized and padded to before it is fed into the PoseNet model. The larger the value, the more accurate the model at the cost of speed. Set this to a smaller value to increase speed at the cost of accuracy. If a number is provided, the image will be resized and padded to be a square with the same width and height.  If `width` and `height` are provided, the image will be resized and padded to the specified width and height.

 * **multiplier** - Can be one of `1.0`, `0.75`, or `0.50` (The value is used *only* by the MobileNetV1 architecture and not by the ResNet architecture). It is the float multiplier for the depth (number of channels) for all convolution ops. The larger the value, the larger the size of the layers, and more accurate the model at the cost of speed. Set this to a smaller value to increase speed at the cost of accuracy.

 * **quantBytes** - This argument controls the bytes used for weight quantization. The available options are:

   - `4`. 4 bytes per float (no quantization). Leads to highest accuracy and original model size (~90MB).

   - `2`. 2 bytes per float. Leads to slightly lower accuracy and 2x model size reduction (~45MB).
   - `1`. 1 byte per float. Leads to lower accuracy and 4x model size reduction (~22MB).

* **modelUrl** - An optional string that specifies custom url of the model. This is useful for local development or countries that don't have access to the model hosted on GCP.


**By default,** PoseNet loads a MobileNetV1 architecture with a **`0.75`** multiplier.  This is recommended for computers with **mid-range/lower-end GPUs.**  A model with a **`0.50`** multiplier is recommended for **mobile.** The ResNet achitecture is recommended for computers with **even more powerful GPUs**.

### Single-Person Pose Estimation

Single pose estimation is the simpler and faster of the two algorithms. Its ideal use case is for when there is only one person in the image. The disadvantage is that if there are multiple persons in an image, keypoints from both persons will likely be estimated as being part of the same single pose—meaning, for example, that person #1’s left arm and person #2’s right knee might be conflated by the algorithm as belonging to the same pose. Both the MobileNetV1 and the ResNet architecture support single-person pose estimation. The method returns a **single pose**:

```javascript
const net = await posenet.load();

const pose = await net.estimateSinglePose(image, {
  flipHorizontal: false
});
```

#### Params in estimateSinglePose()

* **image** - ImageData|HTMLImageElement|HTMLCanvasElement|HTMLVideoElement
   The input image to feed through the network.
* **inferenceConfig** - an object containing:
  * **flipHorizontal** - Defaults to false.  If the pose should be flipped/mirrored  horizontally.  This should be set to true for videos where the video is by default flipped horizontally (i.e. a webcam), and you want the poses to be returned in the proper orientation.

#### Returns

It returns a `Promise` that resolves with a  **single** `pose`. The `pose` has a confidence score and an array of keypoints indexed by part id, each with a score and position.

#### Example Usage

##### via Script Tag

```html
<html>
  <head>
    <!-- Load TensorFlow.js -->
    <script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs"></script>
    <!-- Load Posenet -->
    <script src="https://cdn.jsdelivr.net/npm/@tensorflow-models/posenet"></script>
 </head>

  <body>
    <img id='cat' src='/images/cat.jpg '/>
  </body>
  <!-- Place your code in the script tag below. You can also use an external .js file -->
  <script>
    var flipHorizontal = false;

    var imageElement = document.getElementById('cat');

    posenet.load().then(function(net) {
      const pose = net.estimateSinglePose(imageElement, {
        flipHorizontal: true
      });
      return pose;
    }).then(function(pose){
      console.log(pose);
    })
  </script>
</html>
```

###### via NPM

```javascript
const posenet = require('@tensorflow-models/posenet');

async function estimatePoseOnImage(imageElement) {
  // load the posenet model from a checkpoint
  const net = await posenet.load();

  const pose = await net.estimateSinglePose(imageElement, {
    flipHorizontal: false
  });
  return pose;
}

const imageElement = document.getElementById('cat');

const pose = estimatePoseOnImage(imageElement);

console.log(pose);

```

which would produce the output:

```json
{
  "score": 0.32371445304906,
  "keypoints": [
    {
      "position": {
        "y": 76.291801452637,
        "x": 253.36747741699
      },
      "part": "nose",
      "score": 0.99539834260941
    },
    {
      "position": {
        "y": 71.10383605957,
        "x": 253.54365539551
      },
      "part": "leftEye",
      "score": 0.98781454563141
    },
    {
      "position": {
        "y": 71.839515686035,
        "x": 246.00454711914
      },
      "part": "rightEye",
      "score": 0.99528175592422
    },
    {
      "position": {
        "y": 72.848854064941,
        "x": 263.08151245117
      },
      "part": "leftEar",
      "score": 0.84029853343964
    },
    {
      "position": {
        "y": 79.956565856934,
        "x": 234.26812744141
      },
      "part": "rightEar",
      "score": 0.92544466257095
    },
    {
      "position": {
        "y": 98.34538269043,
        "x": 399.64068603516
      },
      "part": "leftShoulder",
      "score": 0.99559044837952
    },
    {
      "position": {
        "y": 95.082359313965,
        "x": 458.21868896484
      },
      "part": "rightShoulder",
      "score": 0.99583911895752
    },
    {
      "position": {
        "y": 94.626205444336,
        "x": 163.94561767578
      },
      "part": "leftElbow",
      "score": 0.9518963098526
    },
    {
      "position": {
        "y": 150.2349395752,
        "x": 245.06030273438
      },
      "part": "rightElbow",
      "score": 0.98052614927292
    },
    {
      "position": {
        "y": 113.9603729248,
        "x": 393.19735717773
      },
      "part": "leftWrist",
      "score": 0.94009721279144
    },
    {
      "position": {
        "y": 186.47859191895,
        "x": 257.98034667969
      },
      "part": "rightWrist",
      "score": 0.98029226064682
    },
    {
      "position": {
        "y": 208.5266418457,
        "x": 284.46710205078
      },
      "part": "leftHip",
      "score": 0.97870296239853
    },
    {
      "position": {
        "y": 209.9910736084,
        "x": 243.31219482422
      },
      "part": "rightHip",
      "score": 0.97424703836441
    },
    {
      "position": {
        "y": 281.61965942383,
        "x": 310.93188476562
      },
      "part": "leftKnee",
      "score": 0.98368924856186
    },
    {
      "position": {
        "y": 282.80120849609,
        "x": 203.81164550781
      },
      "part": "rightKnee",
      "score": 0.96947449445724
    },
    {
      "position": {
        "y": 360.62716674805,
        "x": 292.21047973633
      },
      "part": "leftAnkle",
      "score": 0.8883239030838
    },
    {
      "position": {
        "y": 347.41177368164,
        "x": 203.88229370117
      },
      "part": "rightAnkle",
      "score": 0.8255187869072
    }
  ]
}
```

### Keypoints

All keypoints are indexed by part id.  The parts and their ids are:

| Id | Part |
| -- | -- |
| 0 | nose |
| 1 | leftEye |
| 2 | rightEye |
| 3 | leftEar |
| 4 | rightEar |
| 5 | leftShoulder |
| 6 | rightShoulder |
| 7 | leftElbow |
| 8 | rightElbow |
| 9 | leftWrist |
| 10 | rightWrist |
| 11 | leftHip |
| 12 | rightHip |
| 13 | leftKnee |
| 14 | rightKnee |
| 15 | leftAnkle |
| 16 | rightAnkle |



