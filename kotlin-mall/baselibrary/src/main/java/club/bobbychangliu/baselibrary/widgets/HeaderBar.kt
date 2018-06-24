package club.bobbychangliu.baselibrary.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import club.bobbychangliu.baselibrary.R
import kotlinx.android.synthetic.main.layout_header_bar.view.*

class HeaderBar @JvmOverloads constructor(
		context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

	var isShowBack: Boolean
	var titleText: String?
	var rightText: String?

	init {
		val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
		isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack, true)
		titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
		rightText = typedArray.getString(R.styleable.HeaderBar_rightText)

		initView()
		typedArray.recycle()

	}

	private fun initView() {
		// not required to store view
		View.inflate(context, R.layout.layout_header_bar, this)
		mIvLeft.visibility = if (isShowBack) View.VISIBLE else View.GONE
		titleText?.let { mTvTitle.text = it }
		rightText?.let { mTvRight.text = it }
	}
}