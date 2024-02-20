import React, {useEffect, useState} from 'react';
import {Text, TextInput, ToastAndroid, View} from 'react-native';
import crashlytics from '@react-native-firebase/crashlytics';
import {NativeModules, Button} from 'react-native';
const {CupsPrinterModule} = NativeModules;

const App = () => {
  const [printer, setPrinter] = useState('');
  const [url, setUrl] = useState('');
  const onPress = async () => {
    let printerObj = '';
    if (url === '') {
      ToastAndroid.show('Please enter url', ToastAndroid.SHORT);
      return;
    }
    try {
      printerObj = await CupsPrinterModule.connectPrinter('');
      console.log(printer);
    } catch (e) {
      printerObj = e;
      console.log(e, 'here');
    }
    setPrinter(printerObj + '');
  };

  return (
    <View style={{backgroundColor: 'white', flex: 1}}>
      <Text style={{color: 'black', textAlign: 'center'}}>Version: 1.0</Text>
      <TextInput
        style={{backgroundColor: '#D3D3D3', margin: 8, borderRadius: 8}}
        placeholder="Enter url"
        value={url}
        onChangeText={text => {
          setUrl(() => text);
        }}
      />
      <Button title="Connect to Printer" color="#841584" onPress={onPress} />
      <Text style={{color: 'black'}}>{printer}</Text>
    </View>
  );
};

export default App;
