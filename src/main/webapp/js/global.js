function isStringEmpty(s) {
	if (!s) {
		return true;
	} else {
		if (s.replace(/(^s*)|(s*$)/g, "").length == 0) {
			return true;
		} else {
			return false;
		}
	}
}