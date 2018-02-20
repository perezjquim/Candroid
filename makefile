main: update repackage

update:
	@echo "@Updating files...@"
	@cp candroid-java/candroid.jar candroid-deb/opt/candroid/candroid.jar
	@echo "@Files updated!@"

repackage:
	@echo "@Repackaging...@"
	@dpkg-deb --build candroid-deb candroid-install.deb > /dev/null
	@echo "@Repackaged!@"
