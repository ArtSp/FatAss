
import MPSwiftUI

extension Color {
    init(
        hex: Int64
    ) {
        self.init(hex: String(format: "%llX", hex))
    }
}

extension Color {
    static let colors = Colors()
    
    static let lightBlue = Color(hex: colors.LightBlue)
    static let lightBlueGrey = Color(hex: colors.LightBlueGrey)
    static let accentViolet = Color(hex: colors.AccentViolet)
    static let textBlack = Color(hex: colors.TextBlack)
    static let darkGrey = Color(hex: colors.DarkGrey)
}
