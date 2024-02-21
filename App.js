import React, {useEffect, useState} from 'react';
import {Text, TextInput, ToastAndroid, View} from 'react-native';
import {NativeModules, Button} from 'react-native';
const {CupsPrinterModule} = NativeModules;

const App = () => {
  const [printer, setPrinter] = useState('');
  const [url, setUrl] = useState(
    'http://192.168.10.247:631/printers/Brother_DCP-B7535DW_series',
  );
  const [host, setHost] = useState('');
  const onPress = async () => {
    let printerObj = '';
    if (url === '') {
      ToastAndroid.show('Please enter url', ToastAndroid.SHORT);
      return;
    }
    try {
      let strs = url.split(':');
      setHost(() => strs[1].slice(2));
      printerObj = await CupsPrinterModule.connectPrinter(
        url,
        strs[1].slice(2),
      );
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
      {host && (
        <>
          <Text style={{color: 'black'}}>Url: {url}</Text>
          <Text style={{color: 'black'}}>Host: {host}</Text>
        </>
      )}

      <Text style={{color: 'black'}}>{printer}</Text>
    </View>
  );
};

export default App;
