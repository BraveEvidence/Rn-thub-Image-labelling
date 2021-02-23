# react-native-rn-thub-image-labelling

Image labelling for Rn

## Installation

```sh
npm install react-native-rn-thub-image-labelling
```

## Usage

```js
import RnThubImageLabelling from 'react-native-rn-thub-image-labelling';

RnThubImageLabelling.imageLabelDetection(
  imagePath,
  (data) => {
    console.log(data);
  },
  (errorMessage) => {
    console.log(errorMessage);
  }
);
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
