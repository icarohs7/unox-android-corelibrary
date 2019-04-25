package base.corelibrary.domain.bluetooth

import android.bluetooth.BluetoothDevice
import arrow.core.Try
import arrow.core.orNull
import arrow.effects.IO
import com.github.icarohs7.unoxcore.extensions.coroutines.onBackground
import com.github.icarohs7.unoxcore.sideEffectBg
import com.sirvar.bluetoothkit.BluetoothKit
import com.sirvar.bluetoothkit.BluetoothKitSocketInterface
import java.io.Closeable
import java.util.UUID

/**
 * Helper wrapping some utilities used to
 * interact with bluetooth devices
 */
object BluetoothHelper : Closeable {
    private val defaultUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
    private val bluetoothKit by lazy { BluetoothKit() }
    /**
     * Socket of the currently connected device
     */
    val connectedDeviceSocket: BluetoothKitSocketInterface? = Try { bluetoothKit.bluetoothSocket }.orNull()
    val pairedDevices: List<BluetoothDevice> by lazy { bluetoothKit.pairedDevices.toList() }

    /**
     * Whether there's a device connected or not
     */
    var isConnected = false
        private set

    /**
     * Connect to the given bluetooth device, changing the state
     * of this singleton, enabling posterior writes to the device,
     * if a device is already connected, automatically disconnect
     * from it first
     * @return Whether the connection was successful or not
     */
    suspend fun connect(device: BluetoothDevice, uuid: UUID = defaultUUID): IO<Boolean> = sideEffectBg {
        close()
        isConnected = bluetoothKit.connect(device, uuid)
        isConnected
    }

    /**
     * Send the given [data] to the currently connected device
     */
    suspend fun write(data: ByteArray, offset: Int = 0, length: Int = data.size): IO<Unit> = sideEffectBg {
        bluetoothKit.write(data, offset, length)
    }

    /**
     * Send the given [data] to the currently connected device
     */
    suspend fun write(data: String): IO<Unit> = sideEffectBg {
        bluetoothKit.write(data.toByteArray())
    }

    /**
     * Disconnect the device and close all sockets
     */
    override fun close() {
        Try { bluetoothKit.disconnect() }
    }
}