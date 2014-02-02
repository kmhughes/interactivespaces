package interactivespaces.service.image.depth.internal.openni2.libraries;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : NiteCTypes.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("NiTE2") 
public class NitePlane extends StructObject {
	/** C type : NitePoint3f */
	@Field(0) 
	public NitePoint3f point() {
		return this.io.getNativeObjectField(this, 0);
	}
	/** C type : NitePoint3f */
	@Field(0) 
	public NitePlane point(NitePoint3f point) {
		this.io.setNativeObjectField(this, 0, point);
		return this;
	}
	/** C type : NitePoint3f */
	@Field(1) 
	public NitePoint3f normal() {
		return this.io.getNativeObjectField(this, 1);
	}
	/** C type : NitePoint3f */
	@Field(1) 
	public NitePlane normal(NitePoint3f normal) {
		this.io.setNativeObjectField(this, 1, normal);
		return this;
	}
	public NitePlane() {
		super();
	}
	public NitePlane(Pointer pointer) {
		super(pointer);
	}
}
