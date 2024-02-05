import React, {useState} from 'react';
import {Text, View} from 'react-native';
import {NativeModules, Button} from 'react-native';
const {CupsPrinterModule} = NativeModules;

const App = () => {
  const [printer, setPrinter] = useState('');
  const onPress = () => {
    console.log(CupsPrinterModule);
  };

  return (
    <View style={{backgroundColor: 'white', flex: 1}}>
      <Button
        title="Click to invoke your native module!"
        color="#841584"
        onPress={onPress}
      />
      <Text>{printer}</Text>
    </View>
  );
};

export default App;
