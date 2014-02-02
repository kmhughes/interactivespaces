package interactivespaces.service.image.depth.internal.openni2.libraries;
import interactivespaces.service.image.depth.internal.openni2.libraries.OpenNI2Library.OniDeviceInfoCallback;
import interactivespaces.service.image.depth.internal.openni2.libraries.OpenNI2Library.OniDeviceStateCallback;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : OniCTypes.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("OpenNI2") 
public class OniDeviceCallbacks extends StructObject {
	/** C type : OniDeviceInfoCallback */
	@Field(0) 
	public Pointer<OniDeviceInfoCallback > deviceConnected() {
		return this.io.getPointerField(this, 0);
	}
	/** C type : OniDeviceInfoCallback */
	@Field(0) 
	public OniDeviceCallbacks deviceConnected(Pointer<OniDeviceInfoCallback > deviceConnected) {
		this.io.setPointerField(this, 0, deviceConnected);
		return this;
	}
	/** C type : OniDeviceInfoCallback */
	@Field(1) 
	public Pointer<OniDeviceInfoCallback > deviceDisconnected() {
		return this.io.getPointerField(this, 1);
	}
	/** C type : OniDeviceInfoCallback */
	@Field(1) 
	public OniDeviceCallbacks deviceDisconnected(Pointer<OniDeviceInfoCallback > deviceDisconnected) {
		this.io.setPointerField(this, 1, deviceDisconnected);
		return this;
	}
	/** C type : OniDeviceStateCallback */
	@Field(2) 
	public Pointer<OniDeviceStateCallback > deviceStateChanged() {
		return this.io.getPointerField(this, 2);
	}
	/** C type : OniDeviceStateCallback */
	@Field(2) 
	public OniDeviceCallbacks deviceStateChanged(Pointer<OniDeviceStateCallback > deviceStateChanged) {
		this.io.setPointerField(this, 2, deviceStateChanged);
		return this;
	}
	public OniDeviceCallbacks() {
		super();
	}
	public OniDeviceCallbacks(Pointer pointer) {
		super(pointer);
	}
}
