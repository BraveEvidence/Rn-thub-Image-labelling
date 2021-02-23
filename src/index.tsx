import { NativeModules } from 'react-native';

type RnThubImageLabellingType = {
  multiply(a: number, b: number): Promise<number>;
};

const { RnThubImageLabelling } = NativeModules;

export default RnThubImageLabelling as RnThubImageLabellingType;
