package base.corelibrary.presentation.custom

import base.corelibrary.databinding.CustomMaterialDropdownMenuBinding
import base.corelibrary.domain.extensions.selectedItemIndex

val CustomMaterialDropdownMenuBinding.selectedItem: String?
    get() = txtDropdown.text?.toString()

var CustomMaterialDropdownMenuBinding.selectedIndex: Int
    get() = txtDropdown.selectedItemIndex
    set(value) {
        txtDropdown.selectedItemIndex = value
    }

var CustomMaterialDropdownMenuBinding.dropdownText: String?
    get() = txtDropdown.text?.toString()
    set(value) = txtDropdown.setText(value)
