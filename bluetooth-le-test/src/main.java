import tinyb.*;

import java.nio.charset.Charset;
import java.util.List;

public class main {


    //Bluetooth adapter
    private static BluetoothAdapter mBluetoothAdapter;

    //Time Service UUID
    public static String TIME_SERVICE = "00001805-0000-1000-8000-00805f9b34fb";

    public static String LOCAL_TIME_INFO = "00002a0f-0000-1000-8000-00805f9b34fb";

    //Current Time Information Characteristic UUID
    public static String CURRENT_TIME = "00002a2b-0000-1000-8000-00805f9b34fb";



    public static void main(String[] args) {

        //initializes Bluetooth adapter
        final BluetoothManager bluetoothManager = BluetoothManager.getBluetoothManager();

        //get the first bluetooth adapter (here we can select the adapter we want to use)
        mBluetoothAdapter = bluetoothManager.getAdapters().get(0);

        //TODO should add a safety to verify if null or not (bluetooth adapter)

        //get local bluetooth name from the adapter
        System.out.println(mBluetoothAdapter.getName());

        //start discovery and set it to a boolean
        boolean discoveryStarted = mBluetoothAdapter.startDiscovery();

        if (discoveryStarted){



            System.out.println("Discovery Started");

            //get a list of available bluetooth devices (could run a special method to select witch advertiser to chose )
            List<BluetoothDevice> bluetoothDevice = mBluetoothAdapter.getDevices();

            //get the first discover device
            //this is not common good practice your will need to get your bluetooth device with the mac address
            BluetoothDevice remoteDevice = bluetoothDevice.get(0);

            //connect to the device...
            remoteDevice.connect();

            //print DeviceName
            System.out.println(remoteDevice.getName());

            try {

                //get the service from the device by comparing UUIDs
                BluetoothGattService  gattService = BluetoothUtils.getService(remoteDevice, TIME_SERVICE);

                // get it's characteristic using custom methods in the BluetoothUtils
                BluetoothGattCharacteristic gattCharacteristic = BluetoothUtils.getCharacteristic(gattService, CURRENT_TIME);


                //get UUID of the characteristic to verify if we got what we want
                System.out.println("Characteristic UUID: " + gattCharacteristic.getUUID());


                //get the value in a array of bytes
                byte[] gattCharacteristicByte = gattCharacteristic.readValue();

                //print the bytes values with a helper method
                System.out.println(readBytesValues(gattCharacteristicByte));

                //show byte length
                System.out.println("byte length: " + gattCharacteristicByte.length);

                //TODO write values to the descriptor and look into notification
                //TODO understand bytes array and values

                // trying some stuff...
                System.out.println("heoo".getBytes(Charset.forName("UTF-8")));




            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }else{

            System.out.println("discovery failed");
        }







    }


    /**
     * loop over the byte and concatenate the values
     * @param byteArray take in the byte array value obtain by the bluetooth characteristic
     * @return a String containing the byte info
     */
    static public String readBytesValues(byte[] byteArray){
        String values = "";

        for (byte byteInfo: byteArray) {

            values += byteInfo + " ";

        }


        return values;
    }
}
