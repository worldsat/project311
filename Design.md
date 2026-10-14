# DESIGN.md — Nova Mobile Design System & Jetpack Compose (M3) Specification

> **Target Platform:** Android (Jetpack Compose / Material 3) & Flutter M3 Compatible  
> **Brand Identity:** Nova Modern Material Indigo Workspace  
> **Visual Archetype:** Clean Material You, Rounded Full/Organic Geometry, High-Contrast Indigo Accents

---

## 1. GLOBAL DESIGN TOKENS

### 1.1 Color Palette
Tokens mapped to standard Material 3 semantic roles (`androidx.compose.material3.ColorScheme`):

| Token Name | Hex Code | Material 3 Semantic Role & Compose Usage |
| :--- | :--- | :--- |
| `primary` | `#2514A5` | Primary buttons, active tab indicators, brand marks (`colorScheme.primary`) |
| `onPrimary` | `#FFFFFF` | Text and icons on primary containers (`colorScheme.onPrimary`) |
| `primaryContainer` | `#E0E0FF` | Active selection pill, light accent chips (`colorScheme.primaryContainer`) |
| `onPrimaryContainer` | `#08006C` | Text/icons on primary container (`colorScheme.onPrimaryContainer`) |
| `secondary` | `#4F46E5` | Interactive accents, verified badges, icon fills (`colorScheme.secondary`) |
| `onSecondary` | `#FFFFFF` | Contrasting elements over secondary fills |
| `surface` | `#FCF8FF` | Root scaffold background (`colorScheme.surface` / `background`) |
| `onSurface` | `#1A1B2E` | High-emphasis body text, headers, primary titles (`colorScheme.onSurface`) |
| `onSurfaceVariant` | `#5B5D72` | Secondary labels, hints, inactive navigation items (`colorScheme.onSurfaceVariant`) |
| `surfaceVariant` | `#EDE8F8` | Filled text fields, tonal secondary cards (`colorScheme.surfaceVariant`) |
| `surfaceContainerLow` | `#F6F2FF` | Social auth buttons, background for nested sections |
| `surfaceContainer` | `#F1ECFB` | Form card background wrapper, bottom navigation background |
| `surfaceContainerHigh` | `#E8E2F5` | Stat cards, elevated modal components |
| `outline` | `#D6D2E6` | Subtle dividers, border strokes on input cards |
| `outlineVariant` | `#EAE6F5` | Minimal divider lines (`colorScheme.outlineVariant`) |
| `error` | `#BA1A1A` | Validation errors, destructive actions (`colorScheme.error`) |
| `errorContainer` | `#FFDAD6` | Log out button container, alert badge backgrounds |
| `onErrorContainer` | `#410002` | Log out text, error text on containers |

### 1.2 Typography Scale
Font Family: **Roboto Flex** / System Default Sans-Serif (`androidx.compose.material3.Typography`):

| Style Name | Size (sp) | Line Height (sp) | Weight | Compose Typography Mapping |
| :--- | :--- | :--- | :--- | :--- |
| **Display Small** | `32.sp` | `38.sp` | `FontWeight.Bold (700)` | `typography.displaySmall` (Page titles like "Welcome Back", "Create Account") |
| **Headline Medium** | `24.sp` | `30.sp` | `FontWeight.SemiBold (600)` | `typography.headlineMedium` (Profile name "Elena Vance") |
| **Title Medium** | `18.sp` | `24.sp` | `FontWeight.SemiBold (600)` | `typography.titleMedium` (Section headers "Account & Security") |
| **Title Small** | `15.sp` | `20.sp` | `FontWeight.Medium (500)` | `typography.titleSmall` (ListItem titles, card metrics labels) |
| **Body Large** | `15.sp` | `22.sp` | `FontWeight.Normal (400)` | `typography.bodyLarge` (Input field user text, primary reading copy) |
| **Body Medium** | `13.sp` | `18.sp` | `FontWeight.Normal (400)` | `typography.bodyMedium` (Input placeholders, supporting subtitles) |
| **Label Large** | `14.sp` | `20.sp` | `FontWeight.SemiBold (600)` | `typography.labelLarge` (CTA buttons, Tab titles, Badges) |
| **Label Small** | `11.sp` | `15.sp` | `FontWeight.Medium (500)` | `typography.labelSmall` (Footers, helper labels, version text) |

### 1.3 Spacing & Shapes

#### Spacing System (dp)
- `Spacing.xxs`: `4.dp` (Icon padding, inline pill gap)
- `Spacing.xs`: `8.dp` (Badge internal padding, tight element spacing)
- `Spacing.sm`: `12.dp` (Field inner padding, between social buttons)
- `Spacing.md`: `16.dp` (Standard screen horizontal padding, card content padding)
- `Spacing.lg`: `20.dp` (Form field vertical gaps)
- `Spacing.xl`: `24.dp` (Section vertical spacing, top app bar margins)
- `Spacing.xxl`: `32.dp` (Hero header to form separator)

#### Corner Radii (dp)
- `Shape.Small`: `8.dp` (Badges, progress bar tracks)
- `Shape.Medium`: `16.dp` (List item containers, stat cards, social login buttons)
- `Shape.Large`: `24.dp` (Main authentication cards, M3 filled text fields)
- `Shape.Full`: `100.dp` / `CircleShape` (CTA buttons, profile avatar, status dots, icon badges)

---

## 2. SCREEN REFERENCE GALLERY

| Technical Screen ID | Display Title | Structural Purpose & Layout Strategy |
| :--- | :--- | :--- |
| `SCREEN_LOGIN` | `Login - Material 3` | Authentication gateway: Centered brand logo emblem, email/password entry, remember me checkbox, full-width CTA pill, biometrics launcher, and social federation options. |
| `SCREEN_SIGNUP` | `Sign Up - Material 3` | Onboarding/registration: Top navigation back button, consistent brand header, full name, email, password strength meter, confirm password match, Terms of Service agreement checkbox, and submit CTA. |
| `SCREEN_PROFILE` | `User Profile - Material 3` | Account hub: Top app bar with action icons (edit, settings, avatar), verified profile hero with stats matrix, interactive settings lists (`ListItem` with toggles & storage bar), and destructive logout button. |

---

## 3. COMPONENT-LEVEL SPECIFICATIONS

### 3.1 TopAppBar
- **Implementation:** `CenterAlignedTopAppBar` or `TopAppBar` with transparent `TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)`.
- **Elements:**
    - Back action: `IconButton` with `Icons.AutoMirrored.Filled.ArrowBack` wrapped in a subtle circular tonal shape (`CircleShape`, background `#F1ECFB`).
    - Title: Centered text (`TitleMedium`, `onSurface`).
    - Action items: `IconButton` trailing actions (e.g., Edit, Settings) with optional small profile thumbnail badge.

### 3.2 BottomNavigationBar
- **Implementation:** `NavigationBar` with `containerColor = Color(0xFFF1ECFB)`, elevation `0.dp`.
- **Items:** 4 primary destinations (`Home`, `Discover`, `Messages`, `Profile`).
- **Indicator:** `NavigationBarItemDefaults.colors(indicatorColor = Color(0xFF2514A5))` rendering an M3 rounded pill (`CircleShape`) with white icon when selected.

### 3.3 Text Fields (M3 Filled / Surface Style)
- **Container:** `RoundedCornerShape(20.dp)` with background `#EDE8F8` (zero border stroke when unfocused, subtle focus border `#2514A5`).
- **Leading & Trailing Icons:**
    - Leading: Tonal tint `Color(0xFF5B5D72)`.
    - Trailing: Password visibility toggle (`IconButton` with eye icon) or validation checkmark (`Icons.Default.CheckCircle` in `#2514A5`).
- **Compose Equivalent:**
  ```kotlin
  TextField(
      value = state.text,
      onValueChange = { ... },
      shape = RoundedCornerShape(20.dp),
      colors = TextFieldDefaults.colors(
          focusedContainerColor = Color(0xFFEDE8F8),
          unfocusedContainerColor = Color(0xFFEDE8F8),
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent
      ),
      modifier = Modifier.fillMaxWidth().height(56.dp)
  )
  ```

### 3.4 Buttons (Primary CTA & Social)
- **Primary CTA:**
    - Container: Pill-shaped `Button(shape = RoundedCornerShape(100.dp))`, background `#2514A5`, content color `#FFFFFF`.
    - Height: `56.dp`, full width. Icon on trailing side (`Icons.AutoMirrored.Filled.ArrowForward`).
- **Social Auth Buttons:**
    - `FilledTonalButton` with container `#F6F2FF`, content color `#1A1B2E`, `RoundedCornerShape(100.dp)`, height `52.dp`.
    - Row layout containing vector logo + Label Large typography.

### 3.5 Quick Stats & Setting Cards
- **Quick Stats Card:**
    - Container: `Surface(shape = RoundedCornerShape(24.dp), color = Color(0xFFEDE8F8))`.
    - Layout: `Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)` with vertical dividers (`VerticalDivider(color = Color(0xFFD6D2E6))`).
- **Setting List Items:**
    - Compose `ListItem` with leading circular tonal icon box (`Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFFEDE8F8)))`), primary title, supporting subtitle, and trailing `Switch` or `Icon(Icons.Default.ChevronRight)`.

---

## 4. FIXED IMAGE ASSET REGISTRY

| Asset Identifier | Asset Category | Purpose & Rendering Specs | Placeholder Reference |
| :--- | :--- | :--- | :--- |
| `ASSET_LOGO_NOVA` | Vector Brand Mark | Circular infinity-twist gradient emblem (Indigo, Purple, Cyan) placed on a soft white circular badge with subtle drop shadow. | Embedded in header |
| `ASSET_AVATAR_USER` | Photographic Image | Professional profile photo (portrait of smiling female professional), circular clipped with online status badge and verification checkmark. | `Profile Image Hero` |
| `ASSET_ICONS_SOCIAL` | Vector Logos | Official flat vector logos for Google (Multicolor G) and Apple (Solid Dark Apple). | `Social Auth Providers` |

---

## 5. AI IMPLEMENTATION SPECIAL INSTRUCTIONS (For Cursor, Copilot & Code Generators)

```markdown
### JETPACK COMPOSE GENERATION DIRECTIVES:
1. **Architecture & State Hoisting:**
   - Implement all screens using standard MVVM architecture (`ViewModel` with `StateFlow<UiState>`).
   - Screens must accept stateless composable parameters: `(uiState: ScreenUiState, onAction: (ScreenAction) -> Unit)`.
   - Never manage raw mutable state inside nested leaf composables.

2. **Container & Layout Rules:**
   - Root container must always be `Scaffold` with `containerColor = Color(0xFFFCF8FF)`.
   - For scrollable screens (Profile, Forms on small screens), wrap content in `LazyColumn` or `Column(modifier = Modifier.verticalScroll(rememberScrollState()))`.
   - Explicitly handle Window Insets via `Modifier.systemBarsPadding()` or `scaffoldPadding`.

3. **Color Strictness:**
   - CTA Buttons (`Sign In`, `Create Account`) MUST strictly use `#2514A5` with white content text.
   - Text field backgrounds must use `#EDE8F8` with `RoundedCornerShape(20.dp)` and zero bottom underline indicator (`focusedIndicatorColor = Color.Transparent`).
   - Destructive CTA (Logout) MUST use container `#FFDAD6` and content `#410002`.

4. **Component Strictness:**
   - Do NOT use legacy `androidx.compose.material.*` components. Strictly use `androidx.compose.material3.*` (Material 3).
   - Use `SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Color(0xFF2514A5))` for toggles.
   - Use `LinearProgressIndicator(color = Color(0xFF2514A5), trackColor = Color(0xFFEDE8F8), shape = CircleShape)` for progress gauges.
```
