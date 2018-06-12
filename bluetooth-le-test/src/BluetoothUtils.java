import tinyb.*;

import java.util.List;

public class BluetoothUtils {

    // I haven't use this method as I didn't figure out a few thing in the official documentation such as "running" boolean variable
    // see here: https://software.intel.com/en-us/java-for-bluetooth-le-apps
    static BluetoothDevice getDevice(String address) throws InterruptedException  {

        BluetoothManager manager = BluetoothManager.getBluetoothManager();

        BluetoothDevice bluetoothDevice = null;

        for (int i = 0; (i < 15) ; ++i) {

            List<BluetoothDevice> list = manager.getDevices();

            for (BluetoothDevice device : list) {

                System.out.println("DEVICE NAME: " + device.getName() + "DEVICE ADDRESS: " + device.getAddress() );
                /*
                 * Here we check if the address matches.
                 */
                if (device.getAddress().equals(address))
                    bluetoothDevice = device;
            }
            if (bluetoothDevice != null) {
                return bluetoothDevice;
            }
            Thread.sleep(4000);
        }
        return null;
    }


    static BluetoothGattService getService(BluetoothDevice device, String
            UUID) throws InterruptedException {

        System.out.println("Services exposed by device:");

        BluetoothGattService tempService = null;

        List<BluetoothGattService> bluetoothServices = null;

        do {
            bluetoothServices = device.getServices();

            for (BluetoothGattService service : bluetoothServices) {
                System.out.println("UUID: " + service.getUUID());
                if (service.getUUID().equals(UUID))
                    tempService = service;
            }

            Thread.sleep(4000);

        } while (bluetoothServices != null && bluetoothServices.isEmpty());

        return tempService;
    }



    static BluetoothGattCharacteristic getCharacteristic(BluetoothGattService service, String UUID) {

        List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();

        for (BluetoothGattCharacteristic characteristic : characteristics) {

            if (characteristic.getUUID().equals(UUID))

                return characteristic;
        }
        return null;
    }


    static BluetoothGattDescriptor getDescriptor (BluetoothGattCharacteristic characteristic, String UUID) {

        List<BluetoothGattDescriptor> descriptors = characteristic.getDescriptors();

        for (BluetoothGattDescriptor descriptor : descriptors) {

            if (descriptor.getUUID().equals(UUID)) return  descriptor;

        }

        return null;

    }



}
