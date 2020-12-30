package unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CylinderTests.class, PlaneTests.class, Point3DTests.class, PolygonTests.class, SphereTests.class,
		TriangleTests.class, TubeTests.class, VectorTests.class, CameraTests.class, GeometriesTests.class, ImageWriterTest.class, LightsTests.class, RayIntersectionTests.class, RenderTests.class,ShadowTests.class, ReflectionRefractionTests.class, softShadow.class, ThreadTest.class, AdaptiveSuperSamplingTests.class })
public class AllTests {

}
