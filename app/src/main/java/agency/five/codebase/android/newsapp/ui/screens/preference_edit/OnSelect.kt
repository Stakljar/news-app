package agency.five.codebase.android.newsapp.ui.screens.preference_edit

fun onSelect(
    values: List<String>,
    value: String,
    onCheck: (String) -> Unit,
    onUncheck: (String) -> Unit,
) {
    if (values.contains(value)) {
        onUncheck(value)
    } else {
        onCheck(value)
    }
}
