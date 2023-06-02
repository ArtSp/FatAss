
import SwiftUI
import MPSwiftUI

struct SectionsScreen: View {
    
    @ObservedObject var viewModel: IOSSectionsViewModel
//    private var sectionsUseCase: SectionsUseCase
    
//    init(sectionsUseCase: SectionsUseCase) {
    init() {
//        self.sectionsUseCase = sectionsUseCase
        self.viewModel = IOSTranslateViewModel(sectionsUseCase: sectionsUseCase)
    }
    
    var body: some View {
        EmptyView()
    }
}

// MARK: - Preview

struct SectionsScreen_Previews: PreviewProvider {
    
    static var previews: some View {
        SectionsScreen()
    }
}
