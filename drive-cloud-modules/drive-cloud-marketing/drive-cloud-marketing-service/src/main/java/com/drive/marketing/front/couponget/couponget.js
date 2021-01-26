import request from '@/utils/request'


// 查询列表
export function listCouponGet(query) {
  return request({
    url: '/marketing/couponGet/pageList',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getCouponGet(id) {
  return request({
    url: '/marketing/couponGet/' + id,
    method: 'get'
  })
}

// 新增
export function addCouponGet(data) {
  return request({
    url: '/marketing/couponGet',
    method: 'post',
    data: data
  })
}

// 修改
export function updateCouponGet(data) {
  return request({
    url: '/marketing/couponGet',
    method: 'put',
    data: data
  })
}

// 删除
export function delCouponGet(id) {
  return request({
    url: '/marketing/couponGet/' + id,
    method: 'delete'
  })
}

// 导出
export function exportCouponGet(query) {
  return request({
    url: '/marketing/couponGet/exportXls',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}