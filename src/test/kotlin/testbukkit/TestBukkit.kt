package testbukkit

object TestBukkit {
    fun world(build: TestWorld.Builder.() -> Unit): TestWorld {
        return TestWorld.Builder(TestWorld()).apply(build).world
    }
}
