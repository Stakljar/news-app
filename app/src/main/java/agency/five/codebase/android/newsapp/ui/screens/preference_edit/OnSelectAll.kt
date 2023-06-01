package agency.five.codebase.android.newsapp.ui.screens.preference_edit

fun onSelectAll(
    preferenceTypeValues: List<String>,
    values: List<String>,
    onCheck: (String) -> Unit,
    onUncheck: (String) -> Unit,
) {
    if (values.containsAll(preferenceTypeValues)) {
        for (i in preferenceTypeValues) {
            onUncheck(i)
        }
    } else {
        for (i in preferenceTypeValues) {
            onCheck(i)
        }
    }
}
