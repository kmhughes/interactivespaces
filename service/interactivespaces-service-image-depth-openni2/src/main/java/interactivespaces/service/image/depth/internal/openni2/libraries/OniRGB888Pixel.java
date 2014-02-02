package interactivespaces.service.image.depth.internal.openni2.libraries;
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
public class OniRGB888Pixel extends StructObject {
	/** Red value of this pixel. */
	@Field(0) 
	public byte r() {
		return this.io.getByteField(this, 0);
	}
	/** Red value of this pixel. */
	@Field(0) 
	public OniRGB888Pixel r(byte r) {
		this.io.setByteField(this, 0, r);
		return this;
	}
	/** Green value of this pixel. */
	@Field(1) 
	public byte g() {
		return this.io.getByteField(this, 1);
	}
	/** Green value of this pixel. */
	@Field(1) 
	public OniRGB888Pixel g(byte g) {
		this.io.setByteField(this, 1, g);
		return this;
	}
	/** Blue value of this pixel. */
	@Field(2) 
	public byte b() {
		return this.io.getByteField(this, 2);
	}
	/** Blue value of this pixel. */
	@Field(2) 
	public OniRGB888Pixel b(byte b) {
		this.io.setByteField(this, 2, b);
		return this;
	}
	public OniRGB888Pixel() {
		super();
	}
	public OniRGB888Pixel(Pointer pointer) {
		super(pointer);
	}
}
